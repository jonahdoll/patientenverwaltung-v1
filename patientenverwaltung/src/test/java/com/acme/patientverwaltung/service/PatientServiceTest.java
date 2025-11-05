package com.acme.patientverwaltung.service;

import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.repository.PatientRepository;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("unit")
@Tag("service-read")
@ExtendWith(SoftAssertionsExtension.class)
@DisplayName("Geschaeftslogik fuer Lesen testen")
public class PatientServiceTest {
    private static final String ID_VORHANDEN = "00000000-0000-0000-0000-000000000000";
    private static final String NACHNAME_VORHANDEN = "Schmidt";

    private final PatientService service;

    @InjectSoftAssertions
    @SuppressWarnings("NullAway.Init")
    private SoftAssertions softly;

    PatientServiceTest() {
        final var constructor = PatientRepository.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        final PatientRepository repo;
        try {
            repo = (PatientRepository) constructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        service = new PatientService(repo);
    }

    @ParameterizedTest(name = "[{index}] Suche mit vorhandener ID: id={0}")
    @ValueSource(strings = ID_VORHANDEN)
    @DisplayName("Suche Patient mit id")
    public void findById(final String id) {
        //given
        final var patientId = UUID.fromString(id);

        //when
        final var patient = service.findById(patientId);

        //then
        assertThat(patient).isNotNull()
            .extracting(Patient::getId)
            .isEqualTo(patientId);
    }

    @ParameterizedTest(name = "[{index}] Suche mit vorhandenem Nachnamen: nachname={0}")
    @ValueSource(strings = NACHNAME_VORHANDEN)
    @DisplayName("Suche Patient mit vorhandenem Nachnamen")
    void find(final String nachname) {
        //given
        final var params = Map.of("nachname", nachname);

        //when
        final var patienten = service.find(params);

        //then
        softly.assertThat(patienten)
            .isNotNull();
        patienten.stream()
            .map(Patient::getNachname)
            .forEach(nachnameTmp -> softly.assertThat(nachnameTmp)
                .isEqualTo(NACHNAME_VORHANDEN));
    }
}
