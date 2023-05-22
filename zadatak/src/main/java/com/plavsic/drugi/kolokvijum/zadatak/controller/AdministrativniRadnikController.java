package com.plavsic.drugi.kolokvijum.zadatak.controller;


import com.plavsic.drugi.kolokvijum.zadatak.dto.AdministrativniRadnikDTO;
import com.plavsic.drugi.kolokvijum.zadatak.dto.KorisnikPravoPristupaDTO;
import com.plavsic.drugi.kolokvijum.zadatak.dto.PravoPristupaDTO;
import com.plavsic.drugi.kolokvijum.zadatak.model.AdministrativniRadnik;
import com.plavsic.drugi.kolokvijum.zadatak.model.KorisnikPravoPristupa;
import com.plavsic.drugi.kolokvijum.zadatak.model.PravoPristupa;
import com.plavsic.drugi.kolokvijum.zadatak.service.AdministrativniRadnikService;
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
public class AdministrativniRadnikController {

    @Autowired
    private AdministrativniRadnikService administrativniRadnikService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PravoPristupaService pravoPristupaService;



    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/administrativniRadnici",method = RequestMethod.GET)
    public ResponseEntity<List<AdministrativniRadnikDTO>> dobaviSve(){

        List<AdministrativniRadnikDTO> radnici = new ArrayList<>();

        for(AdministrativniRadnik r:administrativniRadnikService.findAll()){
            radnici.add( new AdministrativniRadnikDTO(r.getId(),r.getKorisnickoIme(),null,r.getIme(),r.getPrezime(),
                    r.getJmbg(),r.getDatumRodjenja(),r.getRadnoMesto()));
        }

        return new ResponseEntity<List<AdministrativniRadnikDTO>>(radnici, HttpStatus.OK);
    }

    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/administrativniRadnici/{id}",method = RequestMethod.GET)
    public ResponseEntity<AdministrativniRadnikDTO> dobaviJednog(@PathVariable Long id){

        AdministrativniRadnik radnik = administrativniRadnikService.findById(id);
        if(radnik != null){
            AdministrativniRadnikDTO radnikDTO = new AdministrativniRadnikDTO(radnik.getId(),
                    radnik.getKorisnickoIme(),null, radnik.getIme(), radnik.getPrezime(), radnik.getJmbg(),
                    radnik.getDatumRodjenja(),radnik.getRadnoMesto());

            return new ResponseEntity<AdministrativniRadnikDTO>(radnikDTO, HttpStatus.OK);
        }

        return new ResponseEntity<AdministrativniRadnikDTO>(HttpStatus.NOT_FOUND);
    }

    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/administrativniRadnici",method = RequestMethod.POST)
    public ResponseEntity<AdministrativniRadnikDTO> kreiraj(@RequestBody AdministrativniRadnikDTO radnik){

        String encodedLozinka = passwordEncoder.encode(radnik.getLozinka());

        AdministrativniRadnik ar = new AdministrativniRadnik(null,radnik.getKorisnickoIme(),encodedLozinka,
                radnik.getIme(),radnik.getPrezime(),radnik.getJmbg(),radnik.getDatumRodjenja(),radnik.getRadnoMesto());

        ar = administrativniRadnikService.create(ar);


        AdministrativniRadnikDTO radnikDTO = new AdministrativniRadnikDTO(ar.getId(),ar.getKorisnickoIme(),null,
                ar.getIme(), ar.getPrezime(), ar.getJmbg(), ar.getDatumRodjenja(),ar.getRadnoMesto());

        return new ResponseEntity<AdministrativniRadnikDTO>(radnikDTO,HttpStatus.CREATED);
    }

    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/administrativniRadnici/{id}",method = RequestMethod.PUT)
    public ResponseEntity<AdministrativniRadnikDTO> izmeni(@PathVariable Long id,
                                                           @RequestBody AdministrativniRadnikDTO radnik){

        AdministrativniRadnik r = administrativniRadnikService.findById(id);

        if(r != null){

            Set<
                    KorisnikPravoPristupa> korisnikPravoPristupa = new HashSet<>();

            if(radnik.getKorisnikPravoPristupa() != null){

                for(KorisnikPravoPristupaDTO kp:radnik.getKorisnikPravoPristupa()){
                    PravoPristupa pravoPristupa = pravoPristupaService.findById(kp.getPravoPristupa().getId());
                    if(pravoPristupa != null){
                        korisnikPravoPristupa.add(new KorisnikPravoPristupa(kp.getId(),r,pravoPristupa));
                    }else{
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }

                r.setKorisnikPravoPristupa(korisnikPravoPristupa);
            }

            String encodedLozinka = passwordEncoder.encode(radnik.getLozinka());
            r.setKorisnickoIme(radnik.getKorisnickoIme());
            r.setLozinka(encodedLozinka);
            r.setIme(radnik.getIme());
            r.setPrezime(radnik.getPrezime());
            r.setDatumRodjenja(radnik.getDatumRodjenja());
            r.setJmbg(radnik.getJmbg());
            r.setRadnoMesto(radnik.getRadnoMesto());

            r = administrativniRadnikService.update(r);

            Set<KorisnikPravoPristupaDTO> korisnikPravoPristupaDTO = new HashSet<>();

            for(KorisnikPravoPristupa kpp:r.getKorisnikPravoPristupa()){
                korisnikPravoPristupaDTO.add(new KorisnikPravoPristupaDTO(kpp.getId(),
                        null,
                        new PravoPristupaDTO(kpp.getPravoPristupa().getId(),kpp.getPravoPristupa().getNaziv())));
            }

            AdministrativniRadnikDTO radnikDTO = new AdministrativniRadnikDTO(r.getId(),r.getKorisnickoIme(),null,
                    r.getIme(),r.getPrezime(),r.getJmbg(),r.getDatumRodjenja(),korisnikPravoPristupaDTO,r.getRadnoMesto());


            return new ResponseEntity<>(radnikDTO,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/administrativniRadnici/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> ukloni(@PathVariable Long id){
        AdministrativniRadnik radnik = administrativniRadnikService.findById(id);
        if(radnik != null){
            administrativniRadnikService.delete(radnik.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


}
