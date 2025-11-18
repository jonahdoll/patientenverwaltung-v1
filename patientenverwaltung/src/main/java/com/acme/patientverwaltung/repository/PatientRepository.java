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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import static com.acme.patientverwaltung.repository.MockDB.PATIENTEN;
import static java.util.UUID.randomUUID;

@Repository
public class PatientRepository {
    private final StableValue<Logger> logger = StableValue.of();

    public PatientRepository() {
    }

    @Nullable
    public Patient findById(final UUID id) {
        getLogger().debug("findById: id={}", id);
        final var result = PATIENTEN.stream()
            .filter(patient -> Objects.equals(patient.getId(), id))
            .findFirst()
            .orElse(null);
        getLogger().debug("findById: result={}", result);
        return result;
    }

    @SuppressWarnings({"ReturnCount", "PMD.AvoidLiteralsInIfCondition"})
    public Collection<Patient> find(final Map<String, String> queryparam) {
        getLogger().debug("find: queryparam={}", queryparam);

        if (queryparam.isEmpty()) {
            return findAll();
        }

        if (queryparam.size() == 1) {
            final var vorname = queryparam.get("vorname");
            if (vorname != null) {
                final var patienten = findByVorname(vorname);
                getLogger().debug("find (vorname): patienten={}", patienten);
                return patienten;
            }

            final var nachname = queryparam.get("nachname");
            if (nachname != null) {
                final var patienten = findByNachname(nachname);
                getLogger().debug("find (nachname): patienten={}", patienten);
                return patienten;
            }

            final var geburtsdatumStr = queryparam.get("geburtsdatum");
            if (geburtsdatumStr != null) {
                try {
                    final LocalDate geburtsdatum = LocalDate.parse(geburtsdatumStr.trim());
                    final var patienten = findByGeburtsdatum(geburtsdatum);
                    getLogger().debug("find (geburtsdatum): patienten={}", patienten);
                    return patienten;
                } catch (DateTimeParseException e) {
                    return Collections.emptyList();
                }
            }
        }

        return Collections.emptyList();
    }

    private Collection<Patient> findByVorname(final String vorname) {
        getLogger().debug("findByVorname: vorname={}", vorname);
        final var patienten = PATIENTEN.stream()
            .filter(patient -> patient.getVorname()
                .contains(vorname))
            .toList();
        getLogger().debug("findByVorname: patienten={}", patienten);
        return patienten;
    }

    private Collection<Patient> findByNachname(final String nachname) {
        getLogger().debug("findByNachname: nachname={}", nachname);
        final var patienten = PATIENTEN.stream()
            .filter(patient -> patient.getNachname()
                .contains(nachname))
            .toList();
        getLogger().debug("findByNachname: patienten={}", patienten);
        return patienten;
    }

    private Collection<Patient> findByGeburtsdatum(final LocalDate geburtsdatum) {
        getLogger().debug("findByGeburtsdatum: geburtsdatum={}", geburtsdatum);
        final var patienten = PATIENTEN.stream()
            .filter(patient -> patient.getGeburtsdatum()
                .equals(geburtsdatum))
            .toList();
        getLogger().debug("findByGeburtsdatum: patienten={}", patienten);
        return  patienten;
    }

    private Collection<Patient> findAll() {
        return PATIENTEN;
    }

    public boolean isEmailExisting(final CharSequence email) {
        getLogger().debug("isEmailExisting: email={}", email);
        final boolean bool = PATIENTEN.stream()
            .anyMatch(patient -> patient.getEmail()
                .contentEquals(email));
        getLogger().debug("isEmailExisting: bool={}", bool);
        return bool;
    }

    public Patient create(final Patient patient) {
        getLogger().debug("create: {}", patient);
        patient.setId(randomUUID());
        PATIENTEN.add(patient);
        getLogger().debug("create: patient={}", patient);
        return patient;
    }

    public void update(final Patient patient) {
        getLogger().debug("update: {}", patient);
        final var index = IntStream.range(0, PATIENTEN.size())
            .filter(i -> Objects.equals(PATIENTEN.get(i)
                .getId(), patient.getId()))
            .findFirst();
        getLogger().trace("update: index={}", index);
        if (index.isEmpty()) {
            return;
        }
        PATIENTEN.set(index.getAsInt(), patient);
        getLogger().debug("update: patient={}", patient);
    }

    private Logger getLogger() {
        return logger.orElseSet(() -> LoggerFactory.getLogger(PatientRepository.class));
    }
}
