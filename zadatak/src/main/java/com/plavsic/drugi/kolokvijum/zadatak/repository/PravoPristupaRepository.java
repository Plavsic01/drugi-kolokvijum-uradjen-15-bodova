package com.plavsic.drugi.kolokvijum.zadatak.repository;


import com.plavsic.drugi.kolokvijum.zadatak.model.PravoPristupa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PravoPristupaRepository extends CrudRepository<PravoPristupa,Long> {
}
