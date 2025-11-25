package com.acme.patientverwaltung.service;

import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.repository.PatientRepository;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/// Geschäftslogik für Patienten
/// ![Klassendiagramm](../../../../../asciidoc/PatientWriteService.svg)
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
@Service
public class PatientWriteService {
    private final PatientRepository repo;
    private final StableValue<Logger> logger = StableValue.of();

    PatientWriteService(final PatientRepository repo) {
        this.repo = repo;
    }

    public Patient create(final Patient patient) {
        getLogger().debug("create: patient={}", patient);

        if (repo.isEmailExisting(patient.getEmail())) {
            throw new EmailExistsException(patient.getEmail());
        }

        final var patientDB = repo.create(patient);
        getLogger().debug("create: {}", patientDB);
        return patientDB;
    }

    public void update(final Patient patient, final UUID id) {
        getLogger().debug("update: patient={}", patient);
        getLogger().debug("update: id={}", id);

        final var email = patient.getEmail();
        final var patientDb = repo.findById(id);
        if (patientDb == null) {
            throw new NotFoundException(id);
        }
        if (!Objects.equals(email, patientDb.getEmail()) && repo.isEmailExisting(email)) {
            getLogger().debug("update: email={} existiert", email);
            throw new EmailExistsException(email);
        }

        patient.setId(id);
        repo.update(patient);
    }

    private Logger getLogger() {
        return logger.orElseSet(() -> LoggerFactory.getLogger(PatientWriteService.class));
    }
}
