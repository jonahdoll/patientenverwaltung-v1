package com.acme.patientverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum AllergienType {
    POLLENALLERGIE("A"),

    HAUSTAUBALLERGIE("H"),

    TIERHAARALLERGIE("T"),

    NAHRUNGSMITTELALLERGIE("N");

    private final String value;

    private AllergienType(final String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static AllergienType fromValue(final String value) {
        return Stream.of(AllergienType.values())
            .filter(allergie -> allergie.value.equalsIgnoreCase(value))
            .findFirst()
            .orElse(null);
    }
}
