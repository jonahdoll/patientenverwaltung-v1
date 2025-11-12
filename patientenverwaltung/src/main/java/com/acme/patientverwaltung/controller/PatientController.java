package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.service.PatientService;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.acme.patientverwaltung.controller.Constants.API_PATH;
import static com.acme.patientverwaltung.controller.Constants.ID_PATTERN;

@RestController
@RequestMapping(API_PATH)
class PatientController {

    private final PatientService service;

    PatientController(final PatientService service) {
        this.service = service;
    }

    @GetMapping(path = "{id:" + ID_PATTERN + "}")
    Patient getById(@PathVariable final UUID id) {
        return service.findById(id);
    }

    @GetMapping
    Collection<Patient> get(@RequestParam final Map<String, String> queryparam) {
        return service.find(queryparam);
    }
}
