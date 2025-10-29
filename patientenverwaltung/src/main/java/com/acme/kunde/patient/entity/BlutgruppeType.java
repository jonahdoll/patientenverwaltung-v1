package com.acme.kunde.patient.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum BlutgruppeType {
    A("A"),

    B("B"),

    AB("AB"),

    O("O");

    private final String value;

    BlutgruppeType(final String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static BlutgruppeType of(final String value) {
        return Stream.of(BlutgruppeType.values())
            .filter(blutgruppe -> blutgruppe.value.equals(value))
            .findFirst()
            .orElse(null);
    }
}
