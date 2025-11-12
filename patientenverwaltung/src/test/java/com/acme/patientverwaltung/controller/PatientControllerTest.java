package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.Patient;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.UriComponentsBuilder;

import static com.acme.patientverwaltung.controller.Constants.API_PATH;
import static com.acme.patientverwaltung.controller.TestConstants.HOST;
import static com.acme.patientverwaltung.controller.TestConstants.REQUEST_FACTORY;
import static com.acme.patientverwaltung.controller.TestConstants.SCHEMA;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Tag("integration")
@Tag("rest")
@Tag("rest-get")
@DisplayName("REST-Schnittstelle fuer GET-Requests testen")
@ExtendWith(SoftAssertionsExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class PatientControllerTest {
    private static final String ID_VORHANDEN = "00000000-0000-0000-0000-000000000000";
    private static final String NACHNAME_VORHANDEN = "Schmidt";

    private static final String NACHNAME_PARAM = "nachname";

    private final PatientRepository patientRepo;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @SuppressFBWarnings("CT")
    PatientControllerTest(@LocalServerPort final int port, final ApplicationContext ctx) {
        assertThat(ctx).isNotNull();
        final var controller = ctx.getBean(PatientController.class);
        assertThat(controller).isNotNull();

        final var uriComponents = UriComponentsBuilder.newInstance()
            .scheme(SCHEMA)
            .host(HOST)
            .port(port)
            .path(API_PATH)
            .build();
        final var baseUrl = uriComponents.toUriString();

        final var restClient = RestClient
            .builder()
            .requestFactory(REQUEST_FACTORY)
            .baseUrl(baseUrl)
            .build();
        final var clientAdapter = RestClientAdapter.create(restClient);
        final var proxyFactory = HttpServiceProxyFactory.builderFor(clientAdapter)
            .build();
        patientRepo = proxyFactory.createClient(PatientRepository.class);
    }

    @ParameterizedTest(name = "[{index}] Suche mit vorhandener ID: id={0}")
    @ValueSource(strings = ID_VORHANDEN)
    @DisplayName("Suche mit vorhandener ID")
    void getById(final String id) {
        //given

        //when
        final var response = patientRepo.getById(id);

        //then
        final var patient = response.getBody();
        assertThat(patient).isNotNull();
        softly.assertThat(patient.getId()
                .toString())
            .isEqualTo(id);
        softly.assertThat(patient.getVorname())
            .isNotNull();
        softly.assertThat(patient.getNachname())
            .isNotNull();
        softly.assertThat(patient.getGeburtsdatum())
            .isNotNull();
        softly.assertThat(patient.getGeburtsdatum())
            .isNotNull();
    }

    @ParameterizedTest(name = "[{index}] Suche mit vorhandenem Nachnamen: nachname={0}")
    @ValueSource(strings = NACHNAME_VORHANDEN)
    @DisplayName("Suche mit vorhandenem Nachnamen")
    void getByNachname(final String nachname) {
        //given
        final var suchparameter = MultiValueMap.fromSingleValue(Map.of(NACHNAME_PARAM, nachname));

        //when
        final var patienten = patientRepo.get(suchparameter);

        //then
        softly.assertThat(patienten)
            .isNotNull()
            .isNotEmpty();
        patienten.stream()
            .map(Patient::getNachname)
            .forEach(nachnameTmp -> softly.assertThat(nachnameTmp)
                .contains(nachnameTmp));
    }
}
