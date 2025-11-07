package com.acme.patientverwaltung.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
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
