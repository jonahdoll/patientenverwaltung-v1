package com.acme.patientverwaltung.controller;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

record TerminDTO(
    @NotNull
    @FutureOrPresent
    LocalDateTime startZeitpunkt,

    @NotNull
    String grund
) {
}
