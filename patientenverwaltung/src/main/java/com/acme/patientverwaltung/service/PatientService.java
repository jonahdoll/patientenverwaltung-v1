package com.acme.patientverwaltung.service;

import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.repository.PatientRepository;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/// Geschäftslogik für Patienten.
/// ![Klassendiagramm](../../../../../asciidoc/PatientService.svg)
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
@Service
public class PatientService {
    private final PatientRepository repo;
    private final StableValue<Logger> logger = StableValue.of();

    public PatientService(final PatientRepository repo) {
        this.repo = repo;
    }

    public Patient findById(final UUID id) {
        getLogger().debug("findById: id={}", id);
        final var patient = repo.findById(id);
        if (patient == null) {
            throw new NotFoundException(id);
        }
        getLogger().debug("findById: patient={}", patient);
        return patient;
    }

    public Collection<Patient> find(final Map<String, String> queryparam) {
        getLogger().debug("find: queryparam={}", queryparam);

        final var patienten = repo.find(queryparam);
        if (patienten.isEmpty()) {
            throw new NotFoundException();
        }

        getLogger().debug("find: patienten={}", patienten);
        return patienten;
    }

    private Logger getLogger() {
        return logger.orElseSet(() -> LoggerFactory.getLogger(PatientService.class));
    }
}
