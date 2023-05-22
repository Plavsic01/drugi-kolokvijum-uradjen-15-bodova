package com.plavsic.drugi.kolokvijum.zadatak.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class KlijentDTO extends KorisnikDTO{

    private String adresa;
    private Set<KarticaDTO> kartice;

    public KlijentDTO() {}

    public KlijentDTO(Long id, String korisnickoIme, String lozinka, String ime, String prezime,
                      String jmbg, LocalDate datumRodjenja, String adresa, Set<KarticaDTO> kartice) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja);
        this.adresa = adresa;
        this.kartice = kartice;
    }

    public KlijentDTO(Long id, String korisnickoIme, String lozinka, String ime, String prezime,
                      String jmbg, LocalDate datumRodjenja,Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa,String adresa) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja,korisnikPravoPristupa);
        this.adresa = adresa;
    }

    public KlijentDTO(Long id, String korisnickoIme, String lozinka, String ime, String prezime,
                      String jmbg, LocalDate datumRodjenja, String adresa) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, datumRodjenja);
        this.adresa = adresa;
    }

    public KlijentDTO(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String jmbg,
                      LocalDate datumRodjenja, Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa, String adresa, Set<KarticaDTO> kartice) {
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

    public Set<KarticaDTO> getKartice() {
        return kartice;
    }

    public void setKartice(Set<KarticaDTO> kartice) {
        this.kartice = kartice;
    }
}
