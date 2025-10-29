package com.acme.kunde.patient.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum VersicherungsType {
    GESETZLICH("G"),

    PRIVAT("P");

    private final String value;

    private VersicherungsType(final String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static VersicherungsType of(final String value) {
        return Stream.of(values())
            .filter(versicherung -> versicherung.value.equalsIgnoreCase(value))
            .findFirst()
            .orElse(null);
    }
}
