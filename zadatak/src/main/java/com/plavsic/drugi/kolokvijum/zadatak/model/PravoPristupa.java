package com.plavsic.drugi.kolokvijum.zadatak.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class PravoPristupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    @OneToMany(mappedBy = "pravoPristupa")
    private Set<KorisnikPravoPristupa> korisnikPravoPristupa;
    public PravoPristupa() {}

    public PravoPristupa(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public PravoPristupa(Long id, String naziv, Set<KorisnikPravoPristupa> korisnikPravoPristupa) {
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

    public Set<KorisnikPravoPristupa> getKorisnikPravoPristupa() {
        return korisnikPravoPristupa;
    }

    public void setKorisnikPravoPristupa(Set<KorisnikPravoPristupa> korisnikPravoPristupa) {
        this.korisnikPravoPristupa = korisnikPravoPristupa;
    }

    @Override
    public String toString() {
        return "PravoPristupa{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", korisnikPravoPristupa=" + korisnikPravoPristupa +
                '}';
    }
}