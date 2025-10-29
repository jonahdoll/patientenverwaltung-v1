package com.acme.patientverwaltung.service;

import java.io.Serial;

public final class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1101909572340666200L;

    public NotFoundException() {
        super("Keine Patient gefunden");
    }
}
