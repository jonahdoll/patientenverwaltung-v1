package com.acme.kunde.patient.controller;

import com.acme.kunde.patient.entity.Patient;
import com.acme.kunde.patient.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(PatientController.API_PATH)
public class PatientController {
    private final PatientService service;

    static final String API_PATH = "/patient";

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    Collection<Patient> get() {
        return service.findAll();
    }
}
