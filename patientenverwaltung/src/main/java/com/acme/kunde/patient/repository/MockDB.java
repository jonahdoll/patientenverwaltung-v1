package com.acme.kunde.patient.repository;

import com.acme.kunde.patient.entity.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.acme.kunde.patient.entity.BlutgruppeType.A;
import static com.acme.kunde.patient.entity.BlutgruppeType.B;
import static com.acme.kunde.patient.entity.GeschlechtType.MAENNLICH;
import static com.acme.kunde.patient.entity.GeschlechtType.WEIBLICH;
import static com.acme.kunde.patient.entity.MedikamentType.IBUPROFEN;
import static com.acme.kunde.patient.entity.MedikamentType.SIMVASTATIN;
import static com.acme.kunde.patient.entity.VorerkrankungType.RUECKENSCHMERZEN;
import static com.acme.kunde.patient.entity.VorerkrankungType.SCHLAGANFALL;

final class MockDB {
    static final List<Patient> PATIENTEN;

    static {
        PATIENTEN = Stream.of(
            PatientBuilder.getBuilder()
                .setId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .setVorname("Max")
                .setNachname("Müller")
                .setGeschlecht(MAENNLICH)
                .setGeburtsdatum(LocalDate.of(2025, 10, 29))
                .setKrankenakte(KrankenakteBuilder.getBuilder().setBlutgruppe(B).setErstellungsdatum(LocalDate.of(2025, 10, 29)).setMedikamente(List.of(SIMVASTATIN)).setVorerkrankungen(List.of(SCHLAGANFALL)).build())
                .setTermine(List.of(TerminBuilder.getBuilder().setDatum(LocalDate.of(2025,10,30)).setUhrzeit(LocalTime.of(8, 0)).build()))
                .build(),
            PatientBuilder.getBuilder()
                .setId(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                .setVorname("Anna")
                .setNachname("Schmidt")
                .setGeschlecht(WEIBLICH)
                .setGeburtsdatum(LocalDate.of(2001, 6, 21))
                .setKrankenakte(KrankenakteBuilder.getBuilder().setBlutgruppe(B).setErstellungsdatum(LocalDate.of(2025, 10, 29)).setMedikamente(List.of(IBUPROFEN)).setVorerkrankungen(List.of(RUECKENSCHMERZEN)).build())
                .setTermine(List.of(TerminBuilder.getBuilder().setDatum(LocalDate.of(2025,10,31)).setUhrzeit(LocalTime.of(8, 0)).build()))
                .build(),
            PatientBuilder.getBuilder()
                .setId(UUID.fromString("00000000-0000-0000-0000-000000000002"))
                .setVorname("Lukas")
                .setNachname("Weber")
                .setGeschlecht(MAENNLICH)
                .setGeburtsdatum(LocalDate.of(2003, 6, 10))
                .setKrankenakte(KrankenakteBuilder.getBuilder().setBlutgruppe(A).setErstellungsdatum(LocalDate.of(2025, 10, 29)).setMedikamente(List.of(IBUPROFEN)).setVorerkrankungen(List.of(RUECKENSCHMERZEN)).build())
                .setTermine(List.of(TerminBuilder.getBuilder().setDatum(LocalDate.of(2025,10,31)).setUhrzeit(LocalTime.of(9, 0)).build()))
                .build()
        ).collect(Collectors.toList());
    }

    private MockDB() {
    }
}
