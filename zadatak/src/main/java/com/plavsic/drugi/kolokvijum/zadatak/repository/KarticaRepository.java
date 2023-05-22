package com.plavsic.drugi.kolokvijum.zadatak.repository;


import com.plavsic.drugi.kolokvijum.zadatak.model.Kartica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KarticaRepository extends CrudRepository<Kartica,Long> {
    List<Kartica> findAllByKlijentId(Long klijentId);
}
