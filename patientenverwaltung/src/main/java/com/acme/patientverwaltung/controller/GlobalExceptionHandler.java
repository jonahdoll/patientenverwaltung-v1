package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.service.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
class GlobalExceptionHandler {
    private final StableValue<Logger> logger = StableValue.of();

    GlobalExceptionHandler() {
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    void onNotFound(final NotFoundException ex) {
        getLogger().debug("onNotFound: {}", ex.getMessage());
    }

    private Logger getLogger() {
        return logger.orElseSet(() -> LoggerFactory.getLogger(GlobalExceptionHandler.class));
    }
}
