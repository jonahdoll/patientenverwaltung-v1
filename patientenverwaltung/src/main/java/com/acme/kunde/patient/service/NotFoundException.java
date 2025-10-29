package com.acme.kunde.patient.service;

public final class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Keine Patient gefunden");
    }
}
