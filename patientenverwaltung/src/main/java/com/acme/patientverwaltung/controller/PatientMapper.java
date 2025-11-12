package com.acme.patientverwaltung.controller;

import com.acme.patientverwaltung.entity.Krankenakte;
import com.acme.patientverwaltung.entity.Patient;
import com.acme.patientverwaltung.entity.Termin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(nullValueIterableMappingStrategy = RETURN_DEFAULT, componentModel = "spring")
interface PatientMapper {
    @Mapping(target = "id", ignore = true)
    Patient toPatient(PatientDTO dto);

    Krankenakte toKrankenakte(KrankenakteDTO dto);

    Termin toTermin(TerminDTO dto);
}
