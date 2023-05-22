package com.plavsic.drugi.kolokvijum.zadatak.dto;

public class KorisnikPravoPristupaDTO {
    private Long id;
    private KorisnikDTO korisnik;
    private PravoPristupaDTO pravoPristupa;

    public KorisnikPravoPristupaDTO() {}

    public KorisnikPravoPristupaDTO(Long id, KorisnikDTO korisnik, PravoPristupaDTO pravoPristupa) {
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

    public KorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }

    public PravoPristupaDTO getPravoPristupa() {
        return pravoPristupa;
    }

    public void setPravoPristupa(PravoPristupaDTO pravoPristupa) {
        this.pravoPristupa = pravoPristupa;
    }

}