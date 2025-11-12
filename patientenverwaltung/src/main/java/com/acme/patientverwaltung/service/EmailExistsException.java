package com.acme.patientverwaltung.service;

import java.io.Serial;

public class EmailExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1591444778427523090L;

    private final String email;

    EmailExistsException(@SuppressWarnings("ParameterHidesMemberVariable") final String email) {
        super("Die Emailadresse " + email + " existiert bereits");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getMessage() {
        return super.getMessage() == null ? "Die Emailadresse existiert bereits." : super.getMessage();
    }
}
