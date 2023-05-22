package com.plavsic.drugi.kolokvijum.zadatak.service;



import com.plavsic.drugi.kolokvijum.zadatak.model.Kartica;
import com.plavsic.drugi.kolokvijum.zadatak.repository.KarticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KarticaService {

    @Autowired
    private KarticaRepository karticaRepository;

    public Iterable<Kartica> findAll(){
        return karticaRepository.findAll();
    }

    public List<Kartica> findAllByKlijentId(Long klijentId){
        return karticaRepository.findAllByKlijentId(klijentId);
    }

    public Kartica findById(Long id){
        return karticaRepository.findById(id).orElse(null);
    }

    public Kartica create(Kartica kartica){
        return karticaRepository.save(kartica);
    }

    public Kartica update(Kartica kartica){
        return karticaRepository.save(kartica);
    }

    public void delete(Long id){
        karticaRepository.deleteById(id);
    }
}

