package com.acme.patientverwaltung.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Patient {
    private UUID id;
    private String vorname;
    private String nachname;
    private String email;
    private LocalDate geburtsdatum;
    private GeschlechtType geschlecht;
    private Krankenakte krankenakte;
    private List<Termin> termine;

    @SuppressWarnings("ParameterNumber")
    public Patient(final UUID id, final String vorname, final String nachname, final String email,
                   final LocalDate geburtsdatum,
                   final GeschlechtType geschlecht, final Krankenakte krankenakte, final List<Termin> termine) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
        this.krankenakte = krankenakte;
        this.termine = termine;
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Patient patient && Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(final String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(final String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(final LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public GeschlechtType getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(final GeschlechtType geschlecht) {
        this.geschlecht = geschlecht;
    }

    public Krankenakte getKrankenakte() {
        return krankenakte;
    }

    public void setKrankenakte(final Krankenakte krankenakte) {
        this.krankenakte = krankenakte;
    }

    public List<Termin> getTermine() {
        return termine;
    }

    public void setTermine(final List<Termin> termine) {
        this.termine = termine;
    }

    @Override
    public String toString() {
        return "Patient{" +
            "id=" + id +
            ", vorname='" + vorname + '\'' +
            ", nachname='" + nachname + '\'' +
            ", email='" + email + '\'' +
            ", geburtsdatum=" + geburtsdatum +
            ", geschlecht=" + geschlecht +
            ", krankenakte=" + krankenakte +
            ", termine=" + termine +
            '}';
    }
}
