package com.plavsic.drugi.kolokvijum.zadatak.repository;


import com.plavsic.drugi.kolokvijum.zadatak.model.KorisnikPravoPristupa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikPravoPristupaRepository extends CrudRepository<KorisnikPravoPristupa,Long> {
}
