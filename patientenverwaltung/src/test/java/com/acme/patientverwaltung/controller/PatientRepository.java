package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.Patient;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
interface PatientRepository {
    @GetExchange("/{id}")
    ResponseEntity<Patient> getById(@PathVariable String id);

    @GetExchange
    List<Patient> get(@RequestParam MultiValueMap<String, String> queryParams);

    @PostExchange
    ResponseEntity<Void> post(@RequestBody PatientDTO patient);
}
