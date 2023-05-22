package com.plavsic.drugi.kolokvijum.zadatak.controller;


import com.plavsic.drugi.kolokvijum.zadatak.dto.KorisnikDTO;
import com.plavsic.drugi.kolokvijum.zadatak.dto.KorisnikPravoPristupaDTO;
import com.plavsic.drugi.kolokvijum.zadatak.dto.PravoPristupaDTO;
import com.plavsic.drugi.kolokvijum.zadatak.model.Korisnik;
import com.plavsic.drugi.kolokvijum.zadatak.model.KorisnikPravoPristupa;
import com.plavsic.drugi.kolokvijum.zadatak.model.PravoPristupa;
import com.plavsic.drugi.kolokvijum.zadatak.service.KorisnikPravoPristupaService;
import com.plavsic.drugi.kolokvijum.zadatak.service.KorisnikService;
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
public class KorisnikPravoPristupaController {

    @Autowired
    private KorisnikPravoPristupaService korisnikPravoPristupaService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PravoPristupaService pravoPristupaService;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/korisnikPravoPristupa",method = RequestMethod.GET)
    public ResponseEntity<List<KorisnikPravoPristupaDTO>> dobaviSve(){
        List<KorisnikPravoPristupaDTO> korisnikPravoPristupaDTO = new ArrayList<>();

        for(KorisnikPravoPristupa k: korisnikPravoPristupaService.findAll()){
            korisnikPravoPristupaDTO.add(new KorisnikPravoPristupaDTO(k.getId(),
                    new KorisnikDTO(k.getKorisnik().getId(),k.getKorisnik().getKorisnickoIme(),null,
                            k.getKorisnik().getIme(),k.getKorisnik().getPrezime(),k.getKorisnik().getJmbg(),k.getKorisnik().getDatumRodjenja()),
                    new PravoPristupaDTO(k.getPravoPristupa().getId(),k.getPravoPristupa().getNaziv())));
        }

        return new ResponseEntity<List<KorisnikPravoPristupaDTO>>(korisnikPravoPristupaDTO, HttpStatus.OK);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/korisnikPravoPristupa/{id}",method = RequestMethod.GET)
    public ResponseEntity<KorisnikPravoPristupaDTO> dobaviJednog(@PathVariable Long id){
        KorisnikPravoPristupa k = korisnikPravoPristupaService.findById(id);

        if(k != null){
            KorisnikPravoPristupaDTO korisnikPravoPristupaDTO = new KorisnikPravoPristupaDTO(k.getId(),
                    new KorisnikDTO(k.getKorisnik().getId(),k.getKorisnik().getKorisnickoIme(),null,
                            k.getKorisnik().getIme(),k.getKorisnik().getPrezime(),k.getKorisnik().getJmbg(),k.getKorisnik().getDatumRodjenja()),
                    new PravoPristupaDTO(k.getPravoPristupa().getId(),k.getPravoPristupa().getNaziv()));

            return new ResponseEntity<KorisnikPravoPristupaDTO>(korisnikPravoPristupaDTO, HttpStatus.OK);
        }

        return new ResponseEntity<KorisnikPravoPristupaDTO>(HttpStatus.NOT_FOUND);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/korisnikPravoPristupa",method = RequestMethod.POST)
    public ResponseEntity<KorisnikPravoPristupaDTO> kreiraj(@RequestBody KorisnikPravoPristupaDTO korisnikPravoPristupa){

        Korisnik k = korisnikService.findById(korisnikPravoPristupa.getKorisnik().getId());
        PravoPristupa pp = pravoPristupaService.findById(korisnikPravoPristupa.getPravoPristupa().getId());

        if(k != null && pp != null){
            KorisnikPravoPristupa kpp = new KorisnikPravoPristupa(null,k,pp);
            kpp = korisnikPravoPristupaService.create(kpp);

            KorisnikPravoPristupaDTO korisnikPravoPristupaDTO = new KorisnikPravoPristupaDTO(kpp.getId(),
                    new KorisnikDTO(kpp.getKorisnik().getId(),kpp.getKorisnik().getKorisnickoIme(),null,
                            kpp.getKorisnik().getIme(),kpp.getKorisnik().getPrezime(),kpp.getKorisnik().getJmbg(),
                            kpp.getKorisnik().getDatumRodjenja()),
                    new PravoPristupaDTO(kpp.getPravoPristupa().getId(),kpp.getPravoPristupa().getNaziv()));

            return new ResponseEntity<KorisnikPravoPristupaDTO>(korisnikPravoPristupaDTO,HttpStatus.CREATED);
        }

        return new ResponseEntity<KorisnikPravoPristupaDTO>(HttpStatus.BAD_REQUEST);

    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/korisnikPravoPristupa/{id}",method = RequestMethod.PUT)
    public ResponseEntity<KorisnikPravoPristupaDTO> izmeni(@PathVariable Long id, @RequestBody KorisnikPravoPristupaDTO
            korisnikPravoPristupaDTO){

        KorisnikPravoPristupa k = korisnikPravoPristupaService.findById(id);

        if(k != null){
            if(korisnikPravoPristupaDTO.getKorisnik() != null && korisnikPravoPristupaDTO.getPravoPristupa() != null){
                Korisnik korisnik = korisnikService.findById(korisnikPravoPristupaDTO.getKorisnik().getId());
                PravoPristupa pravoPristupa = pravoPristupaService.findById(korisnikPravoPristupaDTO.getPravoPristupa().getId());

                if(korisnik != null && pravoPristupa != null){
                    k.setKorisnik(korisnik);
                    k.setPravoPristupa(pravoPristupa);
                    k = korisnikPravoPristupaService.update(k);

                    return new ResponseEntity<KorisnikPravoPristupaDTO>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<KorisnikPravoPristupaDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<KorisnikPravoPristupaDTO>(HttpStatus.BAD_REQUEST);

    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/korisnikPravoPristupa/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> ukloni(@PathVariable Long id){
        KorisnikPravoPristupa p = korisnikPravoPristupaService.findById(id);
        if(p != null){
            korisnikPravoPristupaService.delete(p.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
