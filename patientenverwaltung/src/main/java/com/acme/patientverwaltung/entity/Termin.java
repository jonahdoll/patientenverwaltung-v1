package com.acme.patientverwaltung.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Termin {
    private LocalDate datum;
    private LocalTime uhrzeit;
    private String grund;

    public Termin(final LocalDate datum, final LocalTime uhrzeit, final  String grund) {
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.grund = grund;
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

    public String getGrund() {
        return grund;
    }

    public void setGrund(final String grund) {
        this.grund = grund;
    }

    @Override
    public String toString() {
        return "Termin{" +
            "datum=" + datum +
            ", uhrzeit=" + uhrzeit +
            ", grund='" + grund + '\'' +
            '}';
    }
}
