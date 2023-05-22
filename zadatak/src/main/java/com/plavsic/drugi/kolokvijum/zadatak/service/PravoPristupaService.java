package com.plavsic.drugi.kolokvijum.zadatak.service;


import com.plavsic.drugi.kolokvijum.zadatak.model.PravoPristupa;
import com.plavsic.drugi.kolokvijum.zadatak.repository.PravoPristupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PravoPristupaService {

    @Autowired
    private PravoPristupaRepository pravoPristupaRepository;

    public Iterable<PravoPristupa> findAll(){
        return pravoPristupaRepository.findAll();
    }

    public PravoPristupa findById(Long id){
        return pravoPristupaRepository.findById(id).orElse(null);
    }

    public PravoPristupa create(PravoPristupa pravoPristupa){
        return pravoPristupaRepository.save(pravoPristupa);
    }

    public PravoPristupa update(PravoPristupa pravoPristupa){
        return pravoPristupaRepository.save(pravoPristupa);
    }

    public void delete(Long id){
        pravoPristupaRepository.deleteById(id);
    }
}
