package com.acme.patientverwaltung.repository;

import com.acme.patientverwaltung.entity.Patient;
import java.util.Collection;
import org.springframework.stereotype.Repository;
import static com.acme.patientverwaltung.repository.MockDB.PATIENTEN;

@Repository
public class PatientenRepository {

    public PatientenRepository() {
    }

    public Collection<Patient> findAll() {
        return PATIENTEN;
    }
}
