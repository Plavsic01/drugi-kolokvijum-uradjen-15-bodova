package com.plavsic.drugi.kolokvijum.zadatak.repository;


import com.plavsic.drugi.kolokvijum.zadatak.model.Korisnik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends CrudRepository<Korisnik,Long> {
    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
}
