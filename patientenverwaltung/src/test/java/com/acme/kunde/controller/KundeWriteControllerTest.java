/*
 * Copyright (C) 2022 - present Juergen Zimmermann, Hochschule Karlsruhe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.acme.kunde.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.test.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.UriComponentsBuilder;
import static com.acme.kunde.config.DevConfig.DEV;
import static com.acme.kunde.controller.Constants.API_PATH;
import static com.acme.kunde.controller.Constants.ID_PATTERN;
import static com.acme.kunde.controller.TestConstants.API_VERSION_INSERTER;
import static com.acme.kunde.controller.TestConstants.HOST;
import static com.acme.kunde.controller.TestConstants.REQUEST_FACTORY;
import static com.acme.kunde.controller.TestConstants.SCHEMA;
import static com.acme.kunde.entity.FamilienstandType.LEDIG;
import static com.acme.kunde.entity.GeschlechtType.WEIBLICH;
import static com.acme.kunde.entity.InteresseType.LESEN;
import static com.acme.kunde.entity.InteresseType.REISEN;
import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowableOfType;
import static org.junit.jupiter.api.condition.JRE.JAVA_25;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_CONTENT;

@Tag("integration")
@Tag("rest")
@Tag("rest-write")
@DisplayName("REST-Schnittstelle fuer Schreiben testen")
@ExtendWith(SoftAssertionsExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles(DEV)
@EnabledForJreRange(min = JAVA_25, max = JAVA_25)
@SuppressWarnings({"WriteTag", "PMD.AtLeastOneConstructor"})
class KundeWriteControllerTest {
    private static final String ID_UPDATE_PUT = "00000000-0000-0000-0000-000000000030";
    private static final String ID_DELETE = "00000000-0000-0000-0000-000000000050";

    private static final String NEUER_NACHNAME = "Neuernachname-Rest";
    private static final String NEUE_EMAIL = "email.rest@test.de";
    private static final String NEUES_GEBURTSDATUM = "2024-01-31";
    private static final String CURRENCY_CODE = "EUR";
    private static final String NEUE_HOMEPAGE = "https://test.de";

    private static final String NEUE_PLZ = "12345";
    private static final String NEUER_ORT = "Neuerortrest";

    private static final String NEUER_NACHNAME_INVALID = "?!$";
    private static final String NEUE_EMAIL_INVALID = "email@";
    private static final int NEUE_KATEGORIE_INVALID = 11;
    private static final String NEUES_GEBURTSDATUM_INVALID = "3000-01-31";
    private static final String NEUE_PLZ_INVALID = "1234";

    private final KundeRepository kundeRepo;

    @InjectSoftAssertions
    @SuppressWarnings("NullAway.Init")
    private SoftAssertions softly;

    @SuppressFBWarnings("CT")
    KundeWriteControllerTest(@LocalServerPort final int port, final ApplicationContext ctx) {
        assertThat(ctx).isNotNull();
        final var writeController = ctx.getBean(KundeWriteController.class);
        assertThat(writeController).isNotNull();

        final var uriComponents = UriComponentsBuilder.newInstance()
            .scheme(SCHEMA)
            .host(HOST)
            .port(port)
            .path(API_PATH)
            .build();
        final var baseUrl = uriComponents.toUriString();
        final var restClient = RestClient
            .builder()
            .baseUrl(baseUrl)
            .requestFactory(REQUEST_FACTORY)
            .apiVersionInserter(API_VERSION_INSERTER)
            .build();
        final var clientAdapter = RestClientAdapter.create(restClient);
        final var proxyFactory = HttpServiceProxyFactory.builderFor(clientAdapter).build();
        kundeRepo = proxyFactory.createClient(KundeRepository.class);
    }

    @Nested
    @DisplayName("Erzeugen")
    class Erzeugen {
        @ParameterizedTest(name = "[{index}] Neuanlegen eines neuen Kunden: nachname={0}, email={1}")
        @CsvSource(
            NEUER_NACHNAME + "," + NEUE_EMAIL + "," + NEUES_GEBURTSDATUM + "," + NEUE_HOMEPAGE +
                "," + NEUE_PLZ + "," + NEUER_ORT + "," + CURRENCY_CODE
        )
        @DisplayName("Neuanlegen eines neuen Kunden")
        @SuppressWarnings("BooleanExpressionComplexity")
        void post(final ArgumentsAccessor args) {
            // given
            final var nachname = args.getString(0);
            final var email = args.getString(1);
            final var geburtsdatum = args.get(2, LocalDate.class);
            final var homepage = args.get(3, URL.class);
            final var plz = args.getString(4);
            final var ort = args.getString(5);
            final var waehrung = args.get(6, Currency.class);
            if (nachname == null || email == null || geburtsdatum == null || homepage == null || plz == null ||
                ort == null || waehrung == null) {
                throw new IllegalStateException("Testdaten sind null");
            }

            final var kundeDTO = new KundeDTO(
                nachname,
                email,
                1,
                true,
                geburtsdatum,
                homepage,
                WEIBLICH,
                LEDIG,
                new AdresseDTO(plz, ort),
                List.of(new RechnungDTO(ONE, waehrung)),
                List.of(LESEN, REISEN)
            );

            // when
            final var response = kundeRepo.post(kundeDTO);

            // then
            assertThat(response).isNotNull();
            softly.assertThat(response.getStatusCode()).isEqualTo(CREATED);
            final var location = response.getHeaders().getLocation();
            assertThat(location)
                .isNotNull()
                .isInstanceOf(URI.class);
            assertThat(location.toString()).matches(".*/" + ID_PATTERN + '$');
        }

        @ParameterizedTest(name = "[{index}] Neuanlegen mit ungueltigen Werten: nachname={0}, email={1}")
        @CsvSource(
            NEUER_NACHNAME_INVALID + "," + NEUE_EMAIL_INVALID + "," + NEUE_KATEGORIE_INVALID + "," +
                NEUES_GEBURTSDATUM_INVALID + "," + NEUE_PLZ_INVALID + "," + NEUER_ORT
        )
        @DisplayName("Neuanlegen mit ungueltigen Werten")
        @SuppressWarnings({"DynamicRegexReplaceableByCompiledPattern", "BooleanExpressionComplexity"})
        void postInvalid(final ArgumentsAccessor args) {
            // given
            final var nachname = args.getString(0);
            final var email = args.getString(1);
            final var kategorie = args.getInteger(2);
            final var geburtsdatum = args.get(3, LocalDate.class);
            final var plz = args.getString(4);
            final var ort = args.getString(5);
            if (nachname == null || email == null || kategorie == null || geburtsdatum == null || plz == null ||
                ort == null) {
                throw new IllegalStateException("Testdaten sind null");
            }

            final var kundeDTO = new KundeDTO(
                nachname,
                email,
                kategorie,
                true,
                geburtsdatum,
                null,
                WEIBLICH,
                LEDIG,
                new AdresseDTO(plz, ort),
                null,
                List.of(LESEN, REISEN, REISEN)
            );
            final var violationKeys = List.of(
                "nachname",
                "email",
                "kategorie",
                "geburtsdatum",
                "adresse.plz",
                "interessen"
            );

            // when
            final var exc = catchThrowableOfType(
                HttpClientErrorException.UnprocessableContent.class,
                () -> kundeRepo.post(kundeDTO)
            );

            // then
            assertThat(exc.getStatusCode()).isEqualTo(UNPROCESSABLE_CONTENT);
            final var body = exc.getResponseBodyAs(ProblemDetail.class);
            assertThat(body).isNotNull();
            final var detail = body.getDetail();
            assertThat(detail).isNotNull();
            final var violations = detail.split(", ");
            final var actualViolationKeys = Arrays.stream(violations)
                .map(violation -> violation.substring(0, violation.indexOf(": ")))
                .toList();
            assertThat(actualViolationKeys).containsExactlyInAnyOrderElementsOf(violationKeys);
        }
    }

    @Nested
    @DisplayName("Aendern")
    class Aendern {
        @ParameterizedTest(name = "[{index}] Aendern eines vorhandenen Kunden durch PUT: id={0}")
        @ValueSource(strings = ID_UPDATE_PUT)
        @DisplayName("Aendern eines vorhandenen Kunden durch PUT")
        void put(final String id) {
            // given
            final var kundeOrig = kundeRepo.getById(id).getBody();
            assertThat(kundeOrig).isNotNull();
            final var rechnungenOrig = kundeOrig.getRechnungen();
            final List<RechnungDTO> rechnungen;
            if (rechnungenOrig == null) {
                rechnungen = null;
            } else {
                rechnungen = rechnungenOrig.stream()
                    .map(rechnungOrig -> new RechnungDTO(ONE, rechnungOrig.getWaehrung()))
                    .toList();
            }
            final var adresseOrig = kundeOrig.getAdresse();
            final var adresse = new AdresseDTO(adresseOrig.getPlz(), adresseOrig.getOrt());

            final var kunde = new KundeDTO(
                kundeOrig.getNachname(),
                kundeOrig.getEmail() + "put",
                kundeOrig.getKategorie(),
                kundeOrig.isHasNewsletter(),
                kundeOrig.getGeburtsdatum(),
                kundeOrig.getHomepage(),
                kundeOrig.getGeschlecht(),
                kundeOrig.getFamilienstand(),
                adresse,
                rechnungen,
                kundeOrig.getInteressen()
            );

            // when
            final var response = kundeRepo.put(id, kunde);

            // then
            assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
        }
    }

    @Nested
    @DisplayName("Loeschen")
    class Loeschen {
        @ParameterizedTest(name = "[{index}] Loeschen eines vorhandenen Kunden: id={0}")
        @ValueSource(strings = ID_DELETE)
        @DisplayName("Loeschen eines vorhandenen Kunden")
        void deleteById(final String id) {
            // when
            final var response = kundeRepo.deleteById(id);

            // then
            assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
        }
    }
}
