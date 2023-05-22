package com.plavsic.drugi.kolokvijum.zadatak.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class AdministrativniRadnikDTO extends KorisnikDTO{

    private String radnoMesto;

    public AdministrativniRadnikDTO() {}

    public AdministrativniRadnikDTO(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String jmbg,
                                    LocalDate datumRodjenja, String radnoMesto) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja);
        this.radnoMesto = radnoMesto;
    }

    public AdministrativniRadnikDTO(Long id, String korisnickoIme, String lozinka, String ime, String prezime,
                                    String jmbg, LocalDate datumRodjenja,
                                    Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa,
                                    String radnoMesto) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja, korisnikPravoPristupa);
        this.radnoMesto = radnoMesto;
    }

    public String getRadnoMesto() {
        return radnoMesto;
    }

    public void setRadnoMesto(String radnoMesto) {
        this.radnoMesto = radnoMesto;
    }
}
