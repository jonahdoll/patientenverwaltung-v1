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

/// ValueObject für das Neuanlegen und Ändern einer neuen Krankenakte.
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
/// @param erstellungsdatum Erstellungsdatum
/// @param blutgruppe Blutgruppe
/// @param vorerkrankungen Liste mit den Vorerkrankungen eines Patients
/// @param medikamente Liste mit den Medikamenten eines Patients
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
