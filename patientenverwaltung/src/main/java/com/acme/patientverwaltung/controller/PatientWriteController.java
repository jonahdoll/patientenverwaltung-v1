package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.service.EmailExistsException;
import com.acme.patientverwaltung.service.PatientWriteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import static com.acme.patientverwaltung.controller.Constants.API_PATH;
import static com.acme.patientverwaltung.controller.Constants.ID_PATTERN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
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
        final var patientInput = mapper.toPatient(patientDTO);
        final var patientDB = service.create(patientInput);

        final var location = URI.create(request.getRequestURL()
            .toString() + '/' + patientDB.getId());

        return created(location).build();
    }

    @PutMapping(path = "{id:" + ID_PATTERN + "}")
    @ResponseStatus(NO_CONTENT)
    void put(@PathVariable final UUID id, @RequestBody @Validated final PatientDTO patientDTO) {
        final var patientInput = mapper.toPatient(patientDTO);
        service.update(patientInput, id);
    }

    @ExceptionHandler
    ErrorResponse onConstraintViolations(final MethodArgumentNotValidException ex) {
        final var detailMessages = ex.getDetailMessageArguments();
        final var detail = detailMessages.length == 0 || detailMessages[1] == null
            ? "Constraint Violation"
            : ((String) detailMessages[1]).replace(", and ", ", ");
        return ErrorResponse.create(ex, UNPROCESSABLE_CONTENT, detail);
    }

    @ExceptionHandler
    ErrorResponse onEmailExists(final EmailExistsException ex) {
        return ErrorResponse.create(ex, UNPROCESSABLE_CONTENT, ex.getMessage());
    }

    @ExceptionHandler
    ErrorResponse onMessageNotReadable(final HttpMessageNotReadableException ex) {
        final var msg = ex.getMessage() == null ? "N/A" : ex.getMessage();
        return ErrorResponse.create(ex, BAD_REQUEST, msg);
    }
}
