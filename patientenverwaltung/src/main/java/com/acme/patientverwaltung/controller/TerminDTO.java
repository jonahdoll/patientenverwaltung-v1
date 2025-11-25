package com.acme.patientverwaltung.controller;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/// ValueObject für das Neuanlegen und Ändern eines neuen Termins.
///
/// @author [Jonah Doll](mailto:dojo1024@h-ka.de)
/// @param startZeitpunkt Startzeitpunkt
/// @param grund Grund
record TerminDTO(
    @NotNull
    @FutureOrPresent
    LocalDateTime startZeitpunkt,

    @NotNull
    String grund
) {
}
