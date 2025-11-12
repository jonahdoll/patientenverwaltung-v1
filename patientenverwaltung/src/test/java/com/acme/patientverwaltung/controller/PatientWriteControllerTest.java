package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.BlutgruppeType;
import com.acme.patientverwaltung.entity.GeschlechtType;
import com.acme.patientverwaltung.entity.MedikamentType;
import com.acme.patientverwaltung.entity.VorerkrankungType;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.UriComponentsBuilder;
import static com.acme.patientverwaltung.controller.Constants.API_PATH;
import static com.acme.patientverwaltung.controller.Constants.ID_PATTERN;
import static com.acme.patientverwaltung.controller.TestConstants.HOST;
import static com.acme.patientverwaltung.controller.TestConstants.REQUEST_FACTORY;
import static com.acme.patientverwaltung.controller.TestConstants.SCHEMA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;

@Tag("integration")
@Tag("rest")
@Tag("rest-write")
@DisplayName("REST-Schnittstelle fuer Schreiben testen")
@ExtendWith(SoftAssertionsExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class PatientWriteControllerTest {
    private static final String NEUER_VORNAME = "Paul";
    private static final String NEUER_NACHNAME = "Neubauer";
    private static final String NEUE_EMAIL = "paulneubauer@gmai.com";
    private static final String NEUES_GEBURTSDATUM = "1990-05-15";
    private static final String NEUES_ERSTELLUNGSDATUM = "2025-01-12";
    private static final String NEUER_STARTZEITPUNKT = "2026-03-01T14:30";
    private static final String NEUER_GRUND = "Vorsorge";

    private final PatientRepository patientRepo;

    @InjectSoftAssertions
    private SoftAssertions softly;

    PatientWriteControllerTest(@LocalServerPort final int port, final ApplicationContext ctx) {
        assertThat(ctx).isNotNull();
        final var writeController = ctx.getBean(PatientWriteController.class);
        assertThat(writeController).isNotNull();

        final var uriComponents = UriComponentsBuilder.newInstance()
            .scheme(SCHEMA)
            .host(HOST)
            .port(port)
            .path(API_PATH)
            .build();
        final var baseUrl = uriComponents.toUriString();
        final var restClient = RestClient.builder()
            .baseUrl(baseUrl)
            .requestFactory(REQUEST_FACTORY)
            .build();
        final var clientAdapter = RestClientAdapter.create(restClient);
        final var proxyFactory = HttpServiceProxyFactory.builderFor(clientAdapter)
            .build();
        patientRepo = proxyFactory.createClient(PatientRepository.class);
    }

    @ParameterizedTest(name = "[{index}] Neuanlegen eines neuen Patienten: nachname={0}, email={1}")
    @CsvSource(NEUER_VORNAME + "," + NEUER_NACHNAME + "," + NEUE_EMAIL + "," + NEUES_GEBURTSDATUM + "," +
        NEUES_ERSTELLUNGSDATUM + "," + NEUER_STARTZEITPUNKT + "," + NEUER_GRUND)
    void post(final ArgumentsAccessor args) {
        //given
        final var vorname = args.getString(0);
        final var nachname = args.getString(1);
        final var email = args.getString(2);
        final var geburtsdatum = args.get(3, LocalDate.class);
        final var erstellungsdatum = args.get(4, LocalDate.class);
        final var startZeitpunkt = args.get(5, LocalDateTime.class);
        final var grund = args.getString(6);
        if (vorname == null || nachname == null || email == null || geburtsdatum == null || erstellungsdatum == null ||
            startZeitpunkt == null || grund == null) {
            throw new IllegalStateException("Testdaten sind null");
        }

        final var patientDTO = new PatientDTO(vorname, nachname, email, geburtsdatum, GeschlechtType.MAENNLICH,
            new KrankenakteDTO(erstellungsdatum, BlutgruppeType.GRUPPE_A, List.of(VorerkrankungType.RUECKENSCHMERZEN),
                List.of(MedikamentType.IBUPROFEN)), List.of(new TerminDTO(startZeitpunkt, grund)));

        //when
        final var response = patientRepo.post(patientDTO);

        //then
        assertThat(response).isNotNull();
        softly.assertThat(response.getStatusCode())
            .isEqualTo(CREATED);
        final var location = response.getHeaders()
            .getLocation();
        assertThat(location).isNotNull()
            .isInstanceOf(URI.class);
        assertThat(location.toString()).matches(".*/" + ID_PATTERN + '$');
    }
}
