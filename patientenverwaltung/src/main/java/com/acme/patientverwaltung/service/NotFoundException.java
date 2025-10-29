package com.acme.patientverwaltung.service;

public final class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Keine Patient gefunden");
    }
}
