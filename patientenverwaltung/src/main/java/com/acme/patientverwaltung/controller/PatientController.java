package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.service.PatientService;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PatientController.API_PATH, version = "1.0.0" )
public class PatientController {
    static final String API_PATH = "/patienten";
    private final PatientService service;

    public PatientController(final PatientService service) {
        this.service = service;
    }

    @GetMapping
    Collection<Patient> get() {
        return service.findAll();
    }
}
