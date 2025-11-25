package com.acme.patientverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

/// Enum für Blutgruppe.
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
public enum BlutgruppeType {
    GRUPPE_A("A"),

    GRUPPE_B("B"),

    GRUPPE_AB("AB"),

    GRUPPE_O("O");

    private final String value;

    BlutgruppeType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static BlutgruppeType of(final String value) {
        return Stream.of(values())
            .filter(blutgruppe -> blutgruppe.value.equals(value))
            .findFirst()
            .orElse(null);
    }
}
