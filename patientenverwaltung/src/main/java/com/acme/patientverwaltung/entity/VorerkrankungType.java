package com.acme.patientverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum VorerkrankungType {
    HERZKRANKHEIT("H"),

    RUECKENSCHMERZEN("R"),

    SCHLAGANFALL("S"),

    DEPRESSION("D"),

    ALZHEIMER("A");

    private final String value;

    VorerkrankungType(final String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static VorerkrankungType of(final String value) {
        return Stream.of(VorerkrankungType.values())
            .filter(vorkerkrankung -> vorkerkrankung.value.equalsIgnoreCase(value))
            .findFirst()
            .orElse(null);
    }
}
