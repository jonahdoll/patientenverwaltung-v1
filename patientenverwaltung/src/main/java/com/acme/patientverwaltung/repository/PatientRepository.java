package com.acme.patientverwaltung.repository;

import com.acme.patientverwaltung.entity.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Repository;

import static com.acme.patientverwaltung.repository.MockDB.PATIENTEN;

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

    public Collection<Patient> findAll() {
        return PATIENTEN;
    }
}
