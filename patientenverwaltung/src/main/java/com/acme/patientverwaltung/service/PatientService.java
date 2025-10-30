package com.acme.patientverwaltung.service;

import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.repository.PatientRepository;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository repo;

    public PatientService(final PatientRepository repo) {
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
