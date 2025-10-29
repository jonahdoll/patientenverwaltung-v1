package com.acme.kunde.patient.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Patient {
    private UUID id;
    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;
    private GeschlechtType geschlecht;
    private Krankenakte krankenakte;
    private List<Termin> termine;

    public Patient(UUID id, String vorname, String nachname, LocalDate geburtsdatum, GeschlechtType geschlecht, Krankenakte krankenakte, List<Termin> termine) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
        this.krankenakte = krankenakte;
        this.termine = termine;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Patient patient)) return false;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public GeschlechtType getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(GeschlechtType geschlecht) {
        this.geschlecht = geschlecht;
    }

    public Krankenakte getKrankenakte() {
        return krankenakte;
    }

    public void setKrankenakte(Krankenakte krankenakte) {
        this.krankenakte = krankenakte;
    }

    public List<Termin> getTermine() {
        return termine;
    }

    public void setTermine(List<Termin> termine) {
        this.termine = termine;
    }

    @Override
    public String toString() {
        return "Patient{" +
            "id=" + id +
            ", vorname='" + vorname + '\'' +
            ", nachname='" + nachname + '\'' +
            ", geburtsdatum=" + geburtsdatum +
            ", geschlecht=" + geschlecht +
            ", krankenakte=" + krankenakte +
            ", termine=" + termine +
            '}';
    }
}
