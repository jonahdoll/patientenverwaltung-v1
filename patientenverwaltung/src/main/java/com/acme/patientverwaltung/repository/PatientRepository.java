package com.acme.patientverwaltung.repository;

import com.acme.patientverwaltung.entity.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Repository;
import static com.acme.patientverwaltung.repository.MockDB.PATIENTEN;
import static java.util.UUID.randomUUID;

@Repository
public class PatientRepository {

    public PatientRepository() {
    }

    @Nullable
    public Patient findById(final UUID id) {
        return PATIENTEN.stream()
            .filter(patient -> Objects.equals(patient.getId(), id))
            .findFirst()
            .orElse(null);
    }

    @SuppressWarnings({"ReturnCount", "PMD.AvoidLiteralsInIfCondition"})
    public Collection<Patient> find(final Map<String, String> queryparam) {
        if (queryparam.isEmpty()) {
            return findAll();
        }

        if (queryparam.size() == 1) {
            final var vorname = queryparam.get("vorname");
            if (vorname != null) {
                return findByVorname(vorname);
            }

            final var nachname = queryparam.get("nachname");
            if (nachname != null) {
                return findByNachname(nachname);
            }

            final var geburtsdatumStr = queryparam.get("geburtsdatum");
            if (geburtsdatumStr != null) {
                try {
                    final LocalDate geburtsdatum = LocalDate.parse(geburtsdatumStr.trim());
                    return findByGeburtsdatum(geburtsdatum);
                } catch (DateTimeParseException e) {
                    return Collections.emptyList();
                }
            }
        }

        return Collections.emptyList();
    }

    private Collection<Patient> findByVorname(final String vorname) {
        return PATIENTEN.stream()
            .filter(patient -> patient.getVorname()
                .contains(vorname))
            .toList();
    }

    private Collection<Patient> findByNachname(final String nachname) {
        return PATIENTEN.stream()
            .filter(patient -> patient.getNachname()
                .contains(nachname))
            .toList();
    }

    private Collection<Patient> findByGeburtsdatum(final LocalDate geburtsdatum) {
        return PATIENTEN.stream()
            .filter(patient -> patient.getGeburtsdatum()
                .equals(geburtsdatum))
            .toList();
    }

    private Collection<Patient> findAll() {
        return PATIENTEN;
    }

    public boolean isEmailExisting(final CharSequence email) {
        return PATIENTEN.stream()
            .anyMatch(patient -> patient.getEmail()
                .contentEquals(email));
    }

    public Patient create(final Patient patient) {
        patient.setId(randomUUID());
        PATIENTEN.add(patient);
        return patient;
    }

    public void update(final Patient patient) {
        final var index = IntStream.range(0, PATIENTEN.size())
            .filter(i -> Objects.equals(PATIENTEN.get(i)
                .getId(), patient.getId()))
            .findFirst();
        if (index.isEmpty()) {
            return;
        }
        PATIENTEN.set(index.getAsInt(), patient);
    }
}
