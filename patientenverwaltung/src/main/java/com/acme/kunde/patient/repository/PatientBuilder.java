package com.acme.kunde.patient.repository;

import com.acme.kunde.patient.entity.GeschlechtType;
import com.acme.kunde.patient.entity.Krankenakte;
import com.acme.kunde.patient.entity.Patient;
import com.acme.kunde.patient.entity.Termin;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PatientBuilder {
    private UUID id;
    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;
    private GeschlechtType geschlecht;
    private Krankenakte krankenakte;
    private List<Termin> termine;

    public static PatientBuilder getBuilder() { return new PatientBuilder(); }

    public PatientBuilder setId(final UUID id) {
        this.id = id;
        return this;
    }

    public PatientBuilder setVorname(final String vorname) {
        this.vorname = vorname;
        return this;
    }

    public PatientBuilder setNachname(final String nachname) {
        this.nachname = nachname;
        return this;
    }

    public PatientBuilder setGeburtsdatum(final LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
        return this;
    }

    public PatientBuilder setGeschlecht(final GeschlechtType geschlecht) {
        this.geschlecht = geschlecht;
        return this;
    }

    public PatientBuilder setKrankenakte(final Krankenakte krankenakte) {
        this.krankenakte = krankenakte;
        return this;
    }

    public PatientBuilder setTermine(final List<Termin> termine) {
        this.termine = termine;
        return this;
    }

    public Patient build() {
        return new Patient(id, vorname, nachname, geburtsdatum, geschlecht, krankenakte, termine);
    }
}
