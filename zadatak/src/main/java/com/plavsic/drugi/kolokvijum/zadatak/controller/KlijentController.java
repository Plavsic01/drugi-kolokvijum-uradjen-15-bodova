package com.plavsic.drugi.kolokvijum.zadatak.controller;


import com.plavsic.drugi.kolokvijum.zadatak.dto.KlijentDTO;
import com.plavsic.drugi.kolokvijum.zadatak.dto.KorisnikPravoPristupaDTO;
import com.plavsic.drugi.kolokvijum.zadatak.dto.PravoPristupaDTO;
import com.plavsic.drugi.kolokvijum.zadatak.model.Klijent;
import com.plavsic.drugi.kolokvijum.zadatak.model.KorisnikPravoPristupa;
import com.plavsic.drugi.kolokvijum.zadatak.model.PravoPristupa;
import com.plavsic.drugi.kolokvijum.zadatak.service.KlijentService;
import com.plavsic.drugi.kolokvijum.zadatak.service.PravoPristupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api")
public class KlijentController {

    @Autowired
    private KlijentService klijentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PravoPristupaService pravoPristupaService;

    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/klijenti",method = RequestMethod.GET)
    public ResponseEntity<List<KlijentDTO>> dobaviSve(){

        List<KlijentDTO> klijenti = new ArrayList<>();

        for(Klijent k:klijentService.findAll()){
            klijenti.add( new KlijentDTO(k.getId(),k.getKorisnickoIme(),null,k.getIme(),k.getPrezime(),
                    k.getJmbg(),k.getDatumRodjenja(),k.getAdresa()));
        }

        return new ResponseEntity<List<KlijentDTO>>(klijenti, HttpStatus.OK);
    }

    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/klijenti/{id}",method = RequestMethod.GET)
    public ResponseEntity<KlijentDTO> dobaviSve(@PathVariable Long id){

        Klijent k = klijentService.findById(id);

        if(k != null){

            KlijentDTO KlijentDTO = new KlijentDTO(k.getId(),k.getKorisnickoIme(),null,k.getIme(),k.getPrezime(),
                    k.getJmbg(),k.getDatumRodjenja(),k.getAdresa());

            return new ResponseEntity<KlijentDTO>(KlijentDTO, HttpStatus.OK);
        }

        return new ResponseEntity<KlijentDTO>(HttpStatus.NOT_FOUND);

    }

    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/klijenti",method = RequestMethod.POST)
    public ResponseEntity<KlijentDTO> kreiraj(@RequestBody KlijentDTO klijent){

        String encodedLozinka = passwordEncoder.encode(klijent.getLozinka());

        Klijent k = new Klijent(null,klijent.getKorisnickoIme(),encodedLozinka,
                klijent.getIme(),klijent.getPrezime(),klijent.getJmbg(),klijent.getDatumRodjenja(),klijent.getAdresa());


        KlijentDTO klijentDTO = new KlijentDTO(k.getId(),k.getKorisnickoIme(),null,
                k.getIme(), k.getPrezime(), k.getJmbg(), k.getDatumRodjenja(),k.getAdresa());

        return new ResponseEntity<KlijentDTO>(klijentDTO,HttpStatus.CREATED);
    }


    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/klijenti/{id}",method = RequestMethod.PUT)
    public ResponseEntity<KlijentDTO> izmeni(@PathVariable Long id,
                                                           @RequestBody KlijentDTO klijent){

        Klijent k = klijentService.findById(id);

        if(k != null){

            Set<KorisnikPravoPristupa> korisnikPravoPristupa = new HashSet<>();

            if(klijent.getKorisnikPravoPristupa() != null){

                for(KorisnikPravoPristupaDTO kp:klijent.getKorisnikPravoPristupa()){
                    PravoPristupa pravoPristupa = pravoPristupaService.findById(kp.getPravoPristupa().getId());
                    if(pravoPristupa != null){
                        korisnikPravoPristupa.add(new KorisnikPravoPristupa(kp.getId(),k,pravoPristupa));
                    }else{
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }

                k.setKorisnikPravoPristupa(korisnikPravoPristupa);
            }

            String encodedLozinka = passwordEncoder.encode(klijent.getLozinka());
            k.setKorisnickoIme(klijent.getKorisnickoIme());
            k.setLozinka(encodedLozinka);
            k.setIme(klijent.getIme());
            k.setPrezime(klijent.getPrezime());
            k.setDatumRodjenja(klijent.getDatumRodjenja());
            k.setJmbg(klijent.getJmbg());
            k.setAdresa(klijent.getAdresa());

            k = klijentService.update(k);

            Set<KorisnikPravoPristupaDTO> korisnikPravoPristupaDTO = new HashSet<>();

            for(KorisnikPravoPristupa kpp:k.getKorisnikPravoPristupa()){
                korisnikPravoPristupaDTO.add(new KorisnikPravoPristupaDTO(kpp.getId(),
                        null,
                        new PravoPristupaDTO(kpp.getPravoPristupa().getId(),kpp.getPravoPristupa().getNaziv())));
            }

            KlijentDTO klijentDTO = new KlijentDTO(k.getId(),k.getKorisnickoIme(),null,
                    k.getIme(),k.getPrezime(),k.getJmbg(),k.getDatumRodjenja(),korisnikPravoPristupaDTO,k.getAdresa());

            return new ResponseEntity<>(klijentDTO,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/klijenti/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> ukloni(@PathVariable Long id){
        Klijent k = klijentService.findById(id);
        if(k != null){
            klijentService.delete(k.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


}


