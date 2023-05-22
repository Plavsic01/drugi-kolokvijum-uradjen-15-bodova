package com.plavsic.drugi.kolokvijum.zadatak.service;


import com.plavsic.drugi.kolokvijum.zadatak.model.KorisnikPravoPristupa;
import com.plavsic.drugi.kolokvijum.zadatak.repository.KorisnikPravoPristupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikPravoPristupaService {

    @Autowired
    private KorisnikPravoPristupaRepository korisnikPravoPristupaRepository;

    public Iterable<KorisnikPravoPristupa> findAll(){
        return korisnikPravoPristupaRepository.findAll();
    }

    public KorisnikPravoPristupa findById(Long id){
        return korisnikPravoPristupaRepository.findById(id).orElse(null);
    }

    public KorisnikPravoPristupa create(KorisnikPravoPristupa korisnikPravoPristupa){
        return korisnikPravoPristupaRepository.save(korisnikPravoPristupa);
    }

    public KorisnikPravoPristupa update(KorisnikPravoPristupa korisnikPravoPristupa){
        return korisnikPravoPristupaRepository.save(korisnikPravoPristupa);
    }

    public void delete(Long id){
        korisnikPravoPristupaRepository.deleteById(id);
    }
}
