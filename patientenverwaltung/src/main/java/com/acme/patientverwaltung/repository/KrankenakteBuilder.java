package com.acme.patientverwaltung.repository;

import com.acme.patientverwaltung.entity.BlutgruppeType;
import com.acme.patientverwaltung.entity.Krankenakte;
import com.acme.patientverwaltung.entity.MedikamentType;
import com.acme.patientverwaltung.entity.VorerkrankungType;
import java.time.LocalDate;
import java.util.Set;

@SuppressWarnings({"NullAway.Init", "PMD.AtLeastOneConstructor"})
public class KrankenakteBuilder {
    private LocalDate erstellungsdatum;
    private BlutgruppeType blutgruppe;
    private Set<VorerkrankungType> vorerkrankungen;
    private Set<MedikamentType> medikamente;

    public static KrankenakteBuilder getBuilder() {
        return new KrankenakteBuilder();
    }

    public KrankenakteBuilder setErstellungsdatum(final LocalDate erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
        return this;
    }

    public KrankenakteBuilder setBlutgruppe(final BlutgruppeType blutgruppe) {
        this.blutgruppe = blutgruppe;
        return this;
    }

    public KrankenakteBuilder setVorerkrankungen(final Set<VorerkrankungType> vorerkrankungen) {
        this.vorerkrankungen = vorerkrankungen;
        return this;
    }

    public KrankenakteBuilder setMedikamente(final Set<MedikamentType> medikamente) {
        this.medikamente = medikamente;
        return this;
    }

    public Krankenakte build() {
        return new Krankenakte(erstellungsdatum, blutgruppe, vorerkrankungen, medikamente);
    }
}
