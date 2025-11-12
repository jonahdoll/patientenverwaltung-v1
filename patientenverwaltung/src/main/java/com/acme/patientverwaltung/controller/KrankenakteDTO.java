package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.BlutgruppeType;
import com.acme.patientverwaltung.entity.MedikamentType;
import com.acme.patientverwaltung.entity.VorerkrankungType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.validator.constraints.UniqueElements;
import org.jspecify.annotations.Nullable;

record KrankenakteDTO(
    @NotNull
    @PastOrPresent
    LocalDate erstellungsdatum,

    @NotNull
    BlutgruppeType blutgruppe,

    @UniqueElements
    @Nullable
    List<VorerkrankungType> vorerkrankungen,

    @UniqueElements
    @Nullable
    List<MedikamentType> medikamente
) {
}
