package com.plavsic.drugi.kolokvijum.zadatak.model;

import jakarta.persistence.*;

@Entity
public class Kartica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String broj;
    private String datumIsteka;
    private String cvc;
    private Double stanje;
    @ManyToOne
    private Klijent klijent;

    public Kartica() {}

    public Kartica(Long id, String broj, String datumIsteka, String cvc, Double stanje) {
        this.id = id;
        this.broj = broj;
        this.datumIsteka = datumIsteka;
        this.cvc = cvc;
        this.stanje = stanje;
    }

    public Kartica(Long id, String broj, String datumIsteka, String cvc, Double stanje, Klijent klijent) {
        this.id = id;
        this.broj = broj;
        this.datumIsteka = datumIsteka;
        this.cvc = cvc;
        this.stanje = stanje;
        this.klijent = klijent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(String datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Double getStanje() {
        return stanje;
    }

    public void setStanje(Double stanje) {
        this.stanje = stanje;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }
}
