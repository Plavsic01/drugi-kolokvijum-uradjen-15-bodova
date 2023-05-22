package com.plavsic.drugi.kolokvijum.zadatak.model;

import jakarta.persistence.*;

@Entity
public class KorisnikPravoPristupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Korisnik korisnik;
    @ManyToOne
    private PravoPristupa pravoPristupa;

    public KorisnikPravoPristupa() {}

    public KorisnikPravoPristupa(Long id, Korisnik korisnik, PravoPristupa pravoPristupa) {
        this.id = id;
        this.korisnik = korisnik;
        this.pravoPristupa = pravoPristupa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public PravoPristupa getPravoPristupa() {
        return pravoPristupa;
    }

    public void setPravoPristupa(PravoPristupa pravoPristupa) {
        this.pravoPristupa = pravoPristupa;
    }
}
