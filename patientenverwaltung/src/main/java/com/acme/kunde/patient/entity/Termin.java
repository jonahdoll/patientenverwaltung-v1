package com.acme.kunde.patient.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Termin {
    private LocalDate datum;
    private LocalTime uhrzeit;

    public Termin(LocalDate datum, LocalTime uhrzeit) {
        this.datum = datum;
        this.uhrzeit = uhrzeit;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(LocalTime uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    @Override
    public String toString() {
        return "Termin{" +
            "datum=" + datum +
            ", uhrzeit=" + uhrzeit +
            '}';
    }
}
