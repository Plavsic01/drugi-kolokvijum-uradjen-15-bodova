package com.plavsic.drugi.kolokvijum.zadatak.dto;

import java.util.Set;

public class PravoPristupaDTO {
    private Long id;
    private String naziv;
    private Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa;

    public PravoPristupaDTO() {
    }

    public PravoPristupaDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public PravoPristupaDTO(Long id, String naziv, Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa) {
        this.id = id;
        this.naziv = naziv;
        this.korisnikPravoPristupa = korisnikPravoPristupa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<KorisnikPravoPristupaDTO> getKorisnikPravoPristupa() {
        return korisnikPravoPristupa;
    }

    public void setKorisnikPravoPristupa(Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa) {
        this.korisnikPravoPristupa = korisnikPravoPristupa;
    }

}