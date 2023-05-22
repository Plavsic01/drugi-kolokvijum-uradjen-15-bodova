package com.plavsic.drugi.kolokvijum.zadatak.repository;


import com.plavsic.drugi.kolokvijum.zadatak.model.AdministrativniRadnik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativniRadnikRepository extends CrudRepository<AdministrativniRadnik,Long> {
}
