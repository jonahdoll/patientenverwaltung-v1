package com.acme.patientverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

/// Enum für Medikamente.
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
public enum MedikamentType {
    IBUPROFEN("I"),

    AMPLODIPIN("A"),

    SIMVASTATIN("S");

    private final String value;

    MedikamentType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MedikamentType of(final String value) {
        return Stream.of(values())
            .filter(medikament -> medikament.value.equalsIgnoreCase(value))
            .findFirst()
            .orElse(null);
    }
}
