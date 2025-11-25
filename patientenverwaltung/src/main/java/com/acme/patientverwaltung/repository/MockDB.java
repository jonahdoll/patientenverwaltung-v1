package com.acme.patientverwaltung.repository;

import com.acme.patientverwaltung.entity.Patient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static com.acme.patientverwaltung.entity.BlutgruppeType.GRUPPE_A;
import static com.acme.patientverwaltung.entity.BlutgruppeType.GRUPPE_B;
import static com.acme.patientverwaltung.entity.GeschlechtType.MAENNLICH;
import static com.acme.patientverwaltung.entity.GeschlechtType.WEIBLICH;
import static com.acme.patientverwaltung.entity.MedikamentType.IBUPROFEN;
import static com.acme.patientverwaltung.entity.MedikamentType.SIMVASTATIN;
import static com.acme.patientverwaltung.entity.VorerkrankungType.RUECKENSCHMERZEN;
import static com.acme.patientverwaltung.entity.VorerkrankungType.SCHLAGANFALL;

/// Emulation der Datenbasis für persistente Patienten.
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
@SuppressWarnings({"UtilityClassCanBeEnum", "UtilityClass", "MagicNumber", "RedundantSuppression", "java:S1192"})
final class MockDB {
    static final List<Patient> PATIENTEN;

    static {
        PATIENTEN = Stream.of(PatientBuilder.getBuilder()
                .setId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .setVorname("Max")
                .setNachname("Müller")
                .setEmail("maxmüller@gmail.com")
                .setGeschlecht(MAENNLICH)
                .setGeburtsdatum(LocalDate.of(2025, 10, 29))
                .setKrankenakte(KrankenakteBuilder.getBuilder()
                    .setBlutgruppe(GRUPPE_B)
                    .setErstellungsdatum(LocalDate.of(2025, 10, 29))
                    .setMedikamente(List.of(SIMVASTATIN))
                    .setVorerkrankungen(List.of(SCHLAGANFALL))
                    .build())
                .setTermine(List.of(TerminBuilder.getBuilder()
                    .setStartZeitpunkt(LocalDateTime.of(2025, 10, 30, 8, 0))
                    .setGrund("Untersuchung")
                    .build()))
                .build(), PatientBuilder.getBuilder()
                .setId(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                .setVorname("Anna")
                .setNachname("Schmidt")
                .setEmail("annaschmidt@gmail.com")
                .setGeschlecht(WEIBLICH)
                .setGeburtsdatum(LocalDate.of(2001, 6, 21))
                .setKrankenakte(KrankenakteBuilder.getBuilder()
                    .setBlutgruppe(GRUPPE_B)
                    .setErstellungsdatum(LocalDate.of(2025, 10, 29))
                    .setMedikamente(List.of(IBUPROFEN))
                    .setVorerkrankungen(List.of(RUECKENSCHMERZEN))
                    .build())
                .setTermine(List.of(TerminBuilder.getBuilder()
                    .setStartZeitpunkt(LocalDateTime.of(2025, 10, 31, 8, 0))
                    .setGrund("Kontrolle")
                    .build()))
                .build(), PatientBuilder.getBuilder()
                .setId(UUID.fromString("00000000-0000-0000-0000-000000000002"))
                .setVorname("Lukas")
                .setNachname("Weber")
                .setEmail("lukasweber@gmail.com")
                .setGeschlecht(MAENNLICH)
                .setGeburtsdatum(LocalDate.of(2003, 6, 10))
                .setKrankenakte(KrankenakteBuilder.getBuilder()
                    .setBlutgruppe(GRUPPE_A)
                    .setErstellungsdatum(LocalDate.of(2025, 10, 29))
                    .setMedikamente(List.of(IBUPROFEN))
                    .setVorerkrankungen(List.of(RUECKENSCHMERZEN))
                    .build())
                .setTermine(List.of(TerminBuilder.getBuilder()
                    .setStartZeitpunkt(LocalDateTime.of(2025, 10, 31, 9, 0))
                    .setGrund("Gespraech")
                    .build()))
                .build())
            .collect(Collectors.toList());
    }

    private MockDB() {
    }
}
