package com.plavsic.drugi.kolokvijum.zadatak.controller;


import com.plavsic.drugi.kolokvijum.zadatak.dto.KarticaDTO;
import com.plavsic.drugi.kolokvijum.zadatak.dto.KlijentDTO;
import com.plavsic.drugi.kolokvijum.zadatak.model.Kartica;
import com.plavsic.drugi.kolokvijum.zadatak.model.Klijent;
import com.plavsic.drugi.kolokvijum.zadatak.model.Korisnik;
import com.plavsic.drugi.kolokvijum.zadatak.service.KarticaService;
import com.plavsic.drugi.kolokvijum.zadatak.service.KlijentService;
import com.plavsic.drugi.kolokvijum.zadatak.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class KarticaController {

    @Autowired
    private KarticaService karticaService;

    @Autowired
    private KlijentService klijentService;

    @Autowired
    private KorisnikService korisnikService;

    @Secured({"ROLE_MENADZER","ROLE_ADMIN","ROLE_KLIJENT"})
    @RequestMapping(path = "/kartice",method = RequestMethod.GET)
    public ResponseEntity<List<KarticaDTO>> dobaviSve(Authentication auth){
        String korisnickoIme = auth.getName();
        Klijent loggedKlijent = klijentService.findByKorisnickoIme(korisnickoIme);

        if(loggedKlijent != null){
            List<KarticaDTO> kartice = new ArrayList<>();
            for(Kartica k: karticaService.findAllByKlijentId(loggedKlijent.getId())){
                kartice.add(new KarticaDTO(k.getId(),k.getBroj(),k.getDatumIsteka(),k.getCvc(),k.getStanje()));
            }

            return new ResponseEntity<>(kartice,HttpStatus.OK);

        }else{
            List<KarticaDTO> kartice = new ArrayList<>();

            for(Kartica k: karticaService.findAll()){
                kartice.add(new KarticaDTO(k.getId(),k.getBroj(),k.getDatumIsteka(),k.getCvc(),k.getStanje(),
                        new KlijentDTO(k.getKlijent().getId(),k.getKlijent().getKorisnickoIme(),null,k.getKlijent().getIme(),
                                k.getKlijent().getPrezime(),k.getKlijent().getJmbg(),k.getKlijent().getDatumRodjenja(),k.getKlijent().getAdresa())));
            }

            return new ResponseEntity<>(kartice, HttpStatus.OK);
        }

    }

    @Secured({"ROLE_MENADZER","ROLE_ADMIN","ROLE_KLIJENT"})
    @RequestMapping(path = "/kartice/{id}",method = RequestMethod.GET)
    public ResponseEntity<KarticaDTO> dobaviJednog(@PathVariable Long id,Authentication auth) {

        String korisnickoIme = auth.getName();
        Optional<Korisnik> loggedUser = korisnikService.findByKorisnickoIme(korisnickoIme);

        Kartica k = karticaService.findById(id);

        if (k != null && loggedUser.isPresent()) {

            if(k.getKlijent() == loggedUser.get()) {
                KarticaDTO karticaDTO = new KarticaDTO(k.getId(), k.getBroj(), k.getDatumIsteka(), k.getCvc(),
                        k.getStanje());
                return new ResponseEntity<KarticaDTO>(karticaDTO, HttpStatus.OK);

            }else if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
                    auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {

                KarticaDTO karticaDTO = new KarticaDTO(k.getId(), k.getBroj(), k.getDatumIsteka(), k.getCvc(), k.getStanje(),
                        new KlijentDTO(k.getKlijent().getId(), k.getKlijent().getKorisnickoIme(), null,
                                k.getKlijent().getIme(),
                                k.getKlijent().getPrezime(), k.getKlijent().getJmbg(), k.getKlijent().getDatumRodjenja(),
                                k.getKlijent().getAdresa()));

                return new ResponseEntity<KarticaDTO>(karticaDTO, HttpStatus.OK);

            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @Secured({"ROLE_MENADZER","ROLE_ADMIN"})
    @RequestMapping(path = "/kartice",method = RequestMethod.POST)
    public ResponseEntity<KarticaDTO> kreiraj(@RequestBody KarticaDTO kartica){

        Klijent klijent = klijentService.findById(kartica.getKlijent().getId());

        if(klijent != null){
            Kartica k = new Kartica(kartica.getId(), kartica.getBroj(), kartica.getDatumIsteka(),
                    kartica.getCvc(), kartica.getStanje(),klijent);

            k = karticaService.create(k);

            KarticaDTO karticaDTO = new KarticaDTO(k.getId(),k.getBroj(),k.getDatumIsteka(),k.getCvc(),k.getStanje(),
                    new KlijentDTO(klijent.getId(),klijent.getKorisnickoIme(),null,klijent.getIme(),
                    klijent.getPrezime(),klijent.getJmbg(),klijent.getDatumRodjenja(),klijent.getAdresa()));

            return new ResponseEntity<KarticaDTO>(karticaDTO,HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Secured({"ROLE_MENADZER","ROLE_ADMIN","ROLE_KLIJENT"})
    @RequestMapping(path = "/kartice/{id}",method = RequestMethod.PUT)
    public ResponseEntity<KarticaDTO> izmeni(@PathVariable Long id, @RequestBody KarticaDTO kartica){

        Kartica k = karticaService.findById(id);
        if(k != null){

            k.setBroj(kartica.getBroj());
            k.setCvc(kartica.getCvc());
            k.setDatumIsteka(kartica.getDatumIsteka());
            k.setStanje(kartica.getStanje());


            if(kartica.getKlijent() != null){
                Klijent klijent = klijentService.findById(kartica.getKlijent().getId());
                if(klijent != null){
                    k.setKlijent(klijent);
                }else{
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

            k = karticaService.update(k);

            KarticaDTO karticaDTO = new KarticaDTO(k.getId(),k.getBroj(),k.getDatumIsteka(),k.getCvc(),k.getStanje(),
                    new KlijentDTO(k.getKlijent().getId(),k.getKlijent().getKorisnickoIme(),null,k.getKlijent().getIme(),
                            k.getKlijent().getPrezime(),k.getKlijent().getJmbg(),k.getKlijent().getDatumRodjenja(),k.getKlijent().getAdresa()));

            return new ResponseEntity<KarticaDTO>(karticaDTO,HttpStatus.OK);
        }
        return new ResponseEntity<KarticaDTO>(HttpStatus.NOT_FOUND);
    }


    @Secured({"ROLE_MENADZER","ROLE_ADMIN","ROLE_KLIJENT"})
    @RequestMapping(path = "/kartice/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> ukloni(@PathVariable Long id,Authentication auth){
        String korisnickoIme = auth.getName();
        Optional<Korisnik> loggedUser = korisnikService.findByKorisnickoIme(korisnickoIme);
        Kartica k = karticaService.findById(id);

        if(k != null && loggedUser.isPresent()){

            if(k.getKlijent() == loggedUser.get()){
                karticaService.delete(k.getId());
                return new ResponseEntity<>(HttpStatus.OK);

            }else if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
                    auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))){
                karticaService.delete(k.getId());
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }




}
