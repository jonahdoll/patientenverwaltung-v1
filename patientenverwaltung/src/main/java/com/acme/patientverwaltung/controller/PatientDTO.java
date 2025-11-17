package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.GeschlechtType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import org.jspecify.annotations.Nullable;

record PatientDTO(
    @NotNull
    @Pattern(regexp = VORNAME_PATTERN)
    String vorname,

    @NotNull
    @Pattern(regexp = NACHNAME_PATTERN)
    String nachname,

    @Email
    @NotNull
    String email,

    @PastOrPresent
    LocalDate geburtsdatum,

    GeschlechtType geschlecht,

    @Valid
    KrankenakteDTO krankenakte,

    @Nullable
    List<@Valid TerminDTO> termine
) {
    public static final String VORNAME_PATTERN =
        "[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)?";

    public static final String NACHNAME_PATTERN =
        "(o'|von|von der|von und zu|van)?[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)?";
}
