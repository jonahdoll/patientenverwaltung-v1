package com.acme.patientverwaltung.entity;

import java.time.LocalDate;
import java.util.List;

public class Krankenakte {
    private LocalDate erstellungsdatum;
    private BlutgruppeType blutgruppe;
    private List<VorerkrankungType> vorerkrankungen;
    private List<MedikamentType> medikamente;

    public Krankenakte(LocalDate erstellungsdatum, BlutgruppeType blutgruppe, List<VorerkrankungType> vorerkrankungen, List<MedikamentType> medikamente) {
        this.erstellungsdatum = erstellungsdatum;
        this.blutgruppe = blutgruppe;
        this.vorerkrankungen = vorerkrankungen;
        this.medikamente = medikamente;
    }

    public LocalDate getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(LocalDate erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

    public BlutgruppeType getBlutgruppe() {
        return blutgruppe;
    }

    public void setBlutgruppe(BlutgruppeType blutgruppe) {
        this.blutgruppe = blutgruppe;
    }

    public List<VorerkrankungType> getVorerkrankungen() {
        return vorerkrankungen;
    }

    public void setVorerkrankungen(List<VorerkrankungType> vorerkrankungen) {
        this.vorerkrankungen = vorerkrankungen;
    }

    public List<MedikamentType> getMedikamente() {
        return medikamente;
    }

    public void setMedikamente(List<MedikamentType> medikamente) {
        this.medikamente = medikamente;
    }

    @Override
    public String toString() {
        return "Krankenakte{" +
            "erstellungsdatum=" + erstellungsdatum +
            ", blutgruppe=" + blutgruppe +
            ", vorerkrankungen=" + vorerkrankungen +
            ", medikamente=" + medikamente +
            '}';
    }
}
