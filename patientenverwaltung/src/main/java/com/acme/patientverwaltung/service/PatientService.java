package com.acme.patientverwaltung.service;

import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.repository.PatientRepository;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository repo;

    public PatientService(final PatientRepository repo) {
        this.repo = repo;
    }

    public Patient findById(final UUID id) {
        final var patient = repo.findById(id);
        if (patient == null) {
            throw new NotFoundException(id);
        }
        return patient;
    }

    public Collection<Patient> find(final Map<String, String> queryparam) {
        final var patienten = repo.find(queryparam);
        if (patienten.isEmpty()) {
            throw new NotFoundException();
        }
        return patienten;
    }
}
