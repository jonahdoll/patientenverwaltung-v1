package com.acme.patientverwaltung.service;

import java.io.Serial;
import java.util.UUID;

public final class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1101909572340666200L;

    public NotFoundException() {
        super("Keine Patient gefunden");
    }

    public NotFoundException(final UUID id) {
        super("Patient: " + id + " nicht gefunden");
    }
}
