package com.acme.patientverwaltung.entity;

import java.time.LocalDateTime;

public class Termin {
    private LocalDateTime startZeitpunkt;
    private String grund;

    public Termin(final LocalDateTime startZeitpunkt, final String grund) {
        this.startZeitpunkt = startZeitpunkt;
        this.grund = grund;
    }

    public LocalDateTime getStartZeitpunkt() {
        return startZeitpunkt;
    }

    public void setStartZeitpunkt(final LocalDateTime startZeitpunkt) {
        this.startZeitpunkt = startZeitpunkt;
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
            "startZeitpunkt=" + startZeitpunkt +
            ", grund='" + grund + '\'' +
            '}';
    }
}
