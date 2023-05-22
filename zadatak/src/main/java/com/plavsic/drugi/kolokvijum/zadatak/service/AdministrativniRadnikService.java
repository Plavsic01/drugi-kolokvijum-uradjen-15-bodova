package com.plavsic.drugi.kolokvijum.zadatak.service;


import com.plavsic.drugi.kolokvijum.zadatak.model.AdministrativniRadnik;
import com.plavsic.drugi.kolokvijum.zadatak.repository.AdministrativniRadnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrativniRadnikService {

    @Autowired
    private AdministrativniRadnikRepository administrativniRadnikRepository;

    public Iterable<AdministrativniRadnik> findAll(){
        return administrativniRadnikRepository.findAll();
    }

    public AdministrativniRadnik findById(Long id){
        return administrativniRadnikRepository.findById(id).orElse(null);
    }

    public AdministrativniRadnik create(AdministrativniRadnik administrativniRadnik){
        return administrativniRadnikRepository.save(administrativniRadnik);
    }

    public AdministrativniRadnik update(AdministrativniRadnik administrativniRadnik){
        return administrativniRadnikRepository.save(administrativniRadnik);
    }

    public void delete(Long id){
        administrativniRadnikRepository.deleteById(id);
    }
}
