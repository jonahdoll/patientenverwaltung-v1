package com.acme.patientverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum GeschlechtType {
    MAENNLICH("m"),

    WEIBLICH("w"),

    DIVERS("d");

    private final String value;

    GeschlechtType(final String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static GeschlechtType of(final String value) {
        return Stream.of(GeschlechtType.values())
            .filter(geschlecht -> geschlecht.value.equalsIgnoreCase(value))
            .findFirst()
            .orElse(null);
    }
}
