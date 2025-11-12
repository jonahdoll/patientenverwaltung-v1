package com.acme.patientverwaltung.service;

import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.repository.PatientRepository;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PatientWriteService {
    private final PatientRepository repo;

    PatientWriteService(final PatientRepository repo) {
        this.repo = repo;
    }

    public Patient create(final Patient patient) {
        if (repo.isEmailExisting(patient.getEmail())) {
            throw new EmailExistsException(patient.getEmail());
        }

        return repo.create(patient);
    }

    public void update(final Patient patient, final UUID id) {
        final var email = patient.getEmail();
        final var patientDb = repo.findById(id);
        if (patientDb == null) {
            throw new NotFoundException(id);
        }
        if (!Objects.equals(email, patientDb.getEmail()) && repo.isEmailExisting(email)) {
            throw new EmailExistsException(email);
        }

        patient.setId(id);
        repo.update(patient);
    }
}
