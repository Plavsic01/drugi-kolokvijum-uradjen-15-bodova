package com.plavsic.drugi.kolokvijum.zadatak.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Klijent extends Korisnik {
    private String adresa;
    @OneToMany(mappedBy = "klijent")
    private Set<Kartica> kartice;

    public Klijent() {}

    public Klijent(Long id, String korisnickoIme, String lozinka, String ime,
                   String prezime, String jmbg, LocalDate datumRodjenja, String adresa, Set<Kartica> kartice) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja);
        this.adresa = adresa;
        this.kartice = kartice;
    }

    public Klijent(Long id, String korisnickoIme, String lozinka, String ime,
                   String prezime, String jmbg, LocalDate datumRodjenja, String adresa) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja);
        this.adresa = adresa;
    }

    public Klijent(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String jmbg,
                   LocalDate datumRodjenja,
                   Set<KorisnikPravoPristupa> korisnikPravoPristupa, String adresa, Set<Kartica> kartice) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja, korisnikPravoPristupa);
        this.adresa = adresa;
        this.kartice = kartice;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Set<Kartica> getKartice() {
        return kartice;
    }

    public void setKartice(Set<Kartica> kartice) {
        this.kartice = kartice;
    }
}
