package com.plavsic.drugi.kolokvijum.zadatak.repository;

import com.plavsic.drugi.kolokvijum.zadatak.model.Klijent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KlijentRepository extends CrudRepository<Klijent,Long> {
    Optional<Klijent> findByKorisnickoIme(String korisnickoIme);
}
