package com.acme.kunde.patient.repository;

import com.acme.kunde.patient.entity.Termin;

import java.time.LocalDate;
import java.time.LocalTime;

public class TerminBuilder {
    private LocalDate datum;
    private LocalTime uhrzeit;

    public static TerminBuilder getBuilder() { return new TerminBuilder(); }

    public TerminBuilder setDatum(final LocalDate datum) {
        this.datum = datum;
        return this;
    }

    public TerminBuilder setUhrzeit(final LocalTime uhrzeit) {
        this.uhrzeit = uhrzeit;
        return this;
    }

    public Termin build() {
        return new Termin(datum, uhrzeit);
    }
}
