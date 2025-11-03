package com.acme.patientverwaltung.repository;

import com.acme.patientverwaltung.entity.Patient;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import static com.acme.patientverwaltung.repository.MockDB.PATIENTEN;

@Repository
public class PatientRepository {

    public PatientRepository() {
    }

    public Patient findById(final UUID id) {
        return PATIENTEN.stream()
            .filter(patient -> Objects.equals(patient.getId(), id))
            .findFirst()
            .orElse(null);
    }

    public Collection<Patient> findAll() {
        return PATIENTEN;
    }
}
