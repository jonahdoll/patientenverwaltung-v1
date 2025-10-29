package com.acme.kunde.patient.repository;

import com.acme.kunde.patient.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.acme.kunde.patient.repository.MockDB.PATIENTEN;

@Repository
public class PatientenRepository {

    public PatientenRepository() {
    }

    public Collection<Patient> findAll() { return PATIENTEN; }
}
