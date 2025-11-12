package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.GeschlechtType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;
import org.jspecify.annotations.Nullable;

record PatientDTO(
    @NotNull
    String vorname,

    @NotNull
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
}
