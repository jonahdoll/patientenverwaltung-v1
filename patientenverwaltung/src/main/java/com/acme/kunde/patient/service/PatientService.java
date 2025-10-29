package com.acme.kunde.patient.service;

import com.acme.kunde.patient.entity.Patient;
import com.acme.kunde.patient.repository.PatientenRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PatientService {
    private final PatientenRepository repo;

    public PatientService(PatientenRepository repo) {
        this.repo = repo;
    }

    public Collection<Patient> findAll() {
        final var patienten = repo.findAll();
        if (patienten == null) {
            throw new NotFoundException();
        }
        return patienten;
    }
}
