package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.acme.patientverwaltung.controller.Constants.API_PATH;
import static com.acme.patientverwaltung.controller.Constants.ID_PATTERN;

/// Ein Controller zum Lesen von Patienten
/// ![Klassendiagramm](../../../../../asciidoc/PatientController.svg)
///
/// @author [Jonah Doll](mailto: dojo1024@h-ka.de)
@RestController
@RequestMapping(API_PATH)
class PatientController {
    private static final String SUCHEN_TAG = "Suchen";

    private final PatientService service;
    private final StableValue<Logger> logger = StableValue.of();

    PatientController(final PatientService service) {
        this.service = service;
    }

    @GetMapping(path = "{id:" + ID_PATTERN + "}")
    @Operation(summary = "Suche mit der Patient-ID", tags = SUCHEN_TAG)
    @ApiResponse(responseCode = "200", description = "Patient gefunden")
    @ApiResponse(responseCode = "404", description = "Patient nicht gefunden")
    Patient getById(@PathVariable final UUID id) {
        getLogger().debug("getById: id={}, Thread={}", id, Thread.currentThread()
            .getName());

        final var patient = service.findById(id);

        getLogger().debug("getById: patient={}", patient);
        return patient;
    }

    @GetMapping
    @Operation(summary = "Suche mit Query-Parameter", tags = SUCHEN_TAG)
    @ApiResponse(responseCode = "200", description = "Collection mit den Patienten")
    @ApiResponse(responseCode = "404", description = "Keine Patienten gefunden")
    Collection<Patient> get(@RequestParam final Map<String, String> queryparam) {
        getLogger().debug("get: queryparam={}", queryparam);

        final var patienten = service.find(queryparam);

        getLogger().debug("get: patienten={}", patienten);
        return patienten;
    }

    private Logger getLogger() {
        return logger.orElseSet(() -> LoggerFactory.getLogger(PatientController.class));
    }
}
