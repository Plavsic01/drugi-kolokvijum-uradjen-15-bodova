package com.plavsic.drugi.kolokvijum.zadatak.controller;


import com.plavsic.drugi.kolokvijum.zadatak.dto.PravoPristupaDTO;
import com.plavsic.drugi.kolokvijum.zadatak.model.PravoPristupa;
import com.plavsic.drugi.kolokvijum.zadatak.service.PravoPristupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PravoPristupaController {

    @Autowired
    private PravoPristupaService pravoPristupaService;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/pravaPristupa",method = RequestMethod.GET)
    public ResponseEntity<List<PravoPristupaDTO>> dobaviSve(){
        List<PravoPristupaDTO> pravoPristupaDTO = new ArrayList<>();
        for(PravoPristupa p: pravoPristupaService.findAll()){
            pravoPristupaDTO.add(new PravoPristupaDTO(p.getId(),p.getNaziv()));
        }
        return new ResponseEntity<List<PravoPristupaDTO>>(pravoPristupaDTO, HttpStatus.OK);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/pravaPristupa/{id}",method = RequestMethod.GET)
    public ResponseEntity<PravoPristupaDTO> dobaviJednog(@PathVariable Long id){
        PravoPristupa pravoPristupa = pravoPristupaService.findById(id);
        if(pravoPristupa != null){
            PravoPristupaDTO pravoPristupaDTO = new PravoPristupaDTO(pravoPristupa.getId(),pravoPristupa.getNaziv());

            return new ResponseEntity<PravoPristupaDTO>(pravoPristupaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PravoPristupaDTO>(HttpStatus.NOT_FOUND);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/pravaPristupa",method = RequestMethod.POST)
    public ResponseEntity<PravoPristupaDTO> kreiraj(@RequestBody PravoPristupaDTO pravoPristupa){

        PravoPristupa p = new PravoPristupa(null,pravoPristupa.getNaziv());
        p = pravoPristupaService.create(p);

        PravoPristupaDTO pravoPristupaDTO = new PravoPristupaDTO(p.getId(),p.getNaziv());

        return new ResponseEntity<PravoPristupaDTO>(pravoPristupaDTO,HttpStatus.CREATED);

    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/pravaPristupa/{id}",method = RequestMethod.PUT)
    public ResponseEntity<PravoPristupaDTO> izmeni(@PathVariable Long id, @RequestBody PravoPristupaDTO pravoPristupa){

        PravoPristupa p = pravoPristupaService.findById(id);
        if(p != null){
            p.setNaziv(pravoPristupa.getNaziv());
            p = pravoPristupaService.update(p);

            PravoPristupaDTO pravoPristupaDTO = new PravoPristupaDTO(p.getId(),p.getNaziv());
            return new ResponseEntity<PravoPristupaDTO>(pravoPristupaDTO,HttpStatus.OK);
        }
        return new ResponseEntity<PravoPristupaDTO>(HttpStatus.BAD_REQUEST);

    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/pravaPristupa/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> ukloni(@PathVariable Long id){
        PravoPristupa p = pravoPristupaService.findById(id);
        if(p != null){
            pravoPristupaService.delete(p.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
