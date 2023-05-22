package com.plavsic.drugi.kolokvijum.zadatak.dto;

import java.time.LocalDate;
import java.util.Set;

public class KorisnikDTO {
    private Long id;
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private String jmbg;
    private LocalDate datumRodjenja;
    private Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa;

    public KorisnikDTO() {}

    public KorisnikDTO(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, LocalDate datumRodjenja) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datumRodjenja = datumRodjenja;
    }

    public KorisnikDTO(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String jmbg,
                       LocalDate datumRodjenja, Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datumRodjenja = datumRodjenja;
        this.korisnikPravoPristupa = korisnikPravoPristupa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Set<KorisnikPravoPristupaDTO> getKorisnikPravoPristupa() {
        return korisnikPravoPristupa;
    }

    public void setKorisnikPravoPristupa(Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa) {
        this.korisnikPravoPristupa = korisnikPravoPristupa;
    }

}
