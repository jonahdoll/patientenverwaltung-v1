package com.acme.patientverwaltung.entity;

import java.time.LocalDate;
import java.util.Set;

public class Krankenakte {
    private LocalDate erstellungsdatum;
    private BlutgruppeType blutgruppe;
    private Set<VorerkrankungType> vorerkrankungen;
    private Set<MedikamentType> medikamente;

    public Krankenakte(final LocalDate erstellungsdatum, final BlutgruppeType blutgruppe,
                       final Set<VorerkrankungType> vorerkrankungen, final Set<MedikamentType> medikamente) {
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

    public Set<VorerkrankungType> getVorerkrankungen() {
        return vorerkrankungen;
    }

    public void setVorerkrankungen(final Set<VorerkrankungType> vorerkrankungen) {
        this.vorerkrankungen = vorerkrankungen;
    }

    public Set<MedikamentType> getMedikamente() {
        return medikamente;
    }

    public void setMedikamente(final Set<MedikamentType> medikamente) {
        this.medikamente = medikamente;
    }

    @Override
    public String toString() {
        return "Krankenakte{" + "erstellungsdatum=" + erstellungsdatum + ", blutgruppe=" + blutgruppe + ", " +
            "vorerkrankungen=" + vorerkrankungen + ", medikamente=" + medikamente + '}';
    }
}
