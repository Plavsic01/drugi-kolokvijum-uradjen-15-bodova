package com.plavsic.drugi.kolokvijum.zadatak.service;


import com.plavsic.drugi.kolokvijum.zadatak.model.Korisnik;
import com.plavsic.drugi.kolokvijum.zadatak.model.KorisnikPravoPristupa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    KorisnikService korisnikService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Korisnik> korisnik = korisnikService.findByKorisnickoIme(username);
        if(korisnik.isPresent()){
            ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for(KorisnikPravoPristupa pravoPristupa : korisnik.get().getKorisnikPravoPristupa()){
                grantedAuthorities.add(new SimpleGrantedAuthority(pravoPristupa.getPravoPristupa().getNaziv()));
            }
            return new User(korisnik.get().getKorisnickoIme(),korisnik.get().getLozinka(),grantedAuthorities);
        }
        return null;
    }
}
