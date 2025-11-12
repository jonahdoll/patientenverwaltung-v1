package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.service.EmailExistsException;
import com.acme.patientverwaltung.service.PatientWriteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static com.acme.patientverwaltung.controller.Constants.API_PATH;
import static com.acme.patientverwaltung.controller.Constants.ID_PATTERN;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_CONTENT;
import static org.springframework.http.ResponseEntity.created;

@Controller
@Validated
@RequestMapping(API_PATH)
class PatientWriteController {
    private final PatientWriteService service;
    private final PatientMapper mapper;

    PatientWriteController(final PatientWriteService service, final PatientMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    ResponseEntity<Void> post(@RequestBody @Valid final PatientDTO patientDTO, final HttpServletRequest request) {
        final var patienInput = mapper.toPatient(patientDTO);
        final var patientDB = service.create(patienInput);

        final var location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(patientDB.getId())
            .toUri();

        return created(location).build();
    }

    @PutMapping(path = "{id:" + ID_PATTERN + "}")
    @ResponseStatus(NO_CONTENT)
    void put(@PathVariable final UUID id, @RequestBody @Validated final PatientDTO patientDTO,
             final HttpServletRequest request) {
        final var patienInput = mapper.toPatient(patientDTO);
        service.update(patienInput, id);
    }

    @ExceptionHandler
    ErrorResponse onEmailExists(final EmailExistsException ex) {
        return ErrorResponse.create(ex, UNPROCESSABLE_CONTENT, ex.getMessage());
    }
}
