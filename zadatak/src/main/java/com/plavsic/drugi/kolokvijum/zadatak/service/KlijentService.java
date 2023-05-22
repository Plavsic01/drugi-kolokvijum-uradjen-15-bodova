package com.plavsic.drugi.kolokvijum.zadatak.service;


import com.plavsic.drugi.kolokvijum.zadatak.model.Klijent;
import com.plavsic.drugi.kolokvijum.zadatak.repository.KlijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KlijentService {
    @Autowired
    private KlijentRepository klijentRepository;

    public Iterable<Klijent> findAll(){
        return klijentRepository.findAll();
    }

    public Klijent findByKorisnickoIme(String korisnickoIme){
        return klijentRepository.findByKorisnickoIme(korisnickoIme).orElse(null);
    }

    public Klijent findById(Long id){
        return klijentRepository.findById(id).orElse(null);
    }

    public Klijent create(Klijent klijent){
        return klijentRepository.save(klijent);
    }

    public Klijent update(Klijent klijent){
        return klijentRepository.save(klijent);
    }

    public void delete(Long id){
        klijentRepository.deleteById(id);
    }
}
