package com.acme.patientverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

/// Enum für Allergien.
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
public enum AllergienType {
    POLLENALLERGIE("A"),

    HAUSTAUBALLERGIE("H"),

    TIERHAARALLERGIE("T"),

    NAHRUNGSMITTELALLERGIE("N");

    private final String value;

    AllergienType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AllergienType fromValue(final String value) {
        return Stream.of(values())
            .filter(allergie -> allergie.value.equalsIgnoreCase(value))
            .findFirst()
            .orElse(null);
    }
}
