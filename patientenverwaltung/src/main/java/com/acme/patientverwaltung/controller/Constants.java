package com.acme.patientverwaltung.controller;

/// Konstante für die REST-Schnittstelle.
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
final class Constants {
    static final String API_PATH = "/patienten";
    static final String ID_PATTERN = "[\\da-f]{8}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{12}";

    private Constants() {
    }
}
