package com.acme.patientverwaltung.entity;

import java.time.LocalDate;
import java.util.List;

public class Krankenakte {
    private LocalDate erstellungsdatum;
    private BlutgruppeType blutgruppe;
    private List<VorerkrankungType> vorerkrankungen;
    private List<MedikamentType> medikamente;

    public Krankenakte(final LocalDate erstellungsdatum, final BlutgruppeType blutgruppe,
                       final List<VorerkrankungType> vorerkrankungen, final List<MedikamentType> medikamente) {
        this.erstellungsdatum = erstellungsdatum;
        this.blutgruppe = blutgruppe;
        this.vorerkrankungen = vorerkrankungen;
        this.medikamente = medikamente;
    }

    public LocalDate getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(final LocalDate erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

    public BlutgruppeType getBlutgruppe() {
        return blutgruppe;
    }

    public void setBlutgruppe(final BlutgruppeType blutgruppe) {
        this.blutgruppe = blutgruppe;
    }

    public List<VorerkrankungType> getVorerkrankungen() {
        return vorerkrankungen;
    }

    public void setVorerkrankungen(final List<VorerkrankungType> vorerkrankungen) {
        this.vorerkrankungen = vorerkrankungen;
    }

    public List<MedikamentType> getMedikamente() {
        return medikamente;
    }

    public void setMedikamente(final List<MedikamentType> medikamente) {
        this.medikamente = medikamente;
    }

    @Override
    public String toString() {
        return "Krankenakte{" + "erstellungsdatum=" + erstellungsdatum + ", blutgruppe=" + blutgruppe + ", " +
            "vorerkrankungen=" + vorerkrankungen + ", medikamente=" + medikamente + '}';
    }
}
