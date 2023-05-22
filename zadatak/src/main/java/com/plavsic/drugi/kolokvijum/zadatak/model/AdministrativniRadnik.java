package com.plavsic.drugi.kolokvijum.zadatak.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class AdministrativniRadnik extends Korisnik {
    private String radnoMesto;

    public AdministrativniRadnik() {}

    public AdministrativniRadnik(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String jmbg,
                                 LocalDate datumRodjenja,
                                 Set<KorisnikPravoPristupa> korisnikPravoPristupa, String radnoMesto) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja, korisnikPravoPristupa);
        this.radnoMesto = radnoMesto;
    }

    public AdministrativniRadnik(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String jmbg,
                                 LocalDate datumRodjenja, String radnoMesto) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja);
        this.radnoMesto = radnoMesto;
    }

    public String getRadnoMesto() {
        return radnoMesto;
    }

    public void setRadnoMesto(String radnoMesto) {
        this.radnoMesto = radnoMesto;
    }


}
