package com.acme.patientverwaltung.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Termin {
    private LocalDate datum;
    private LocalTime uhrzeit;

    public Termin(final LocalDate datum, final LocalTime uhrzeit) {
        this.datum = datum;
        this.uhrzeit = uhrzeit;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(final LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(final LocalTime uhrzeit) {
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
