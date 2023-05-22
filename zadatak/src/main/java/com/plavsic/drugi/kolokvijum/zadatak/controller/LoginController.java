package com.plavsic.drugi.kolokvijum.zadatak.controller;


import com.plavsic.drugi.kolokvijum.zadatak.dto.KorisnikDTO;
import com.plavsic.drugi.kolokvijum.zadatak.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody KorisnikDTO korisnik){
        System.out.println(passwordEncoder.encode(korisnik.getLozinka()));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                korisnik.getKorisnickoIme(),korisnik.getLozinka());
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = tokenUtils.generateToken(userDetailsService.loadUserByUsername(korisnik.getKorisnickoIme()));
//        System.out.println(jwt);
        return new ResponseEntity<String>(jwt, HttpStatus.OK);
    }

}
