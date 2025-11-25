package com.acme.patientverwaltung.repository;

import com.acme.patientverwaltung.entity.Termin;
import java.time.LocalDateTime;

/// Builder-Klasse für die Klasse [Termin].
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
@SuppressWarnings({"NullAway.Init", "NotNullFieldNotInitialized", "PMD.AtLeastOneConstructor"})
public class TerminBuilder {
    private LocalDateTime startZeitpunkt;
    private String grund;

    public static TerminBuilder getBuilder() {
        return new TerminBuilder();
    }

    public TerminBuilder setStartZeitpunkt(final LocalDateTime startZeitpunkt) {
        this.startZeitpunkt = startZeitpunkt;
        return this;
    }

    public TerminBuilder setGrund(final String grund) {
        this.grund = grund;
        return this;
    }

    public Termin build() {
        return new Termin(startZeitpunkt, grund);
    }
}
