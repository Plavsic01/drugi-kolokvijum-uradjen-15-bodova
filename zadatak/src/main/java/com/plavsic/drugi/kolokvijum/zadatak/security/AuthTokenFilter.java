package com.plavsic.drugi.kolokvijum.zadatak.security;

import com.plavsic.drugi.kolokvijum.zadatak.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;

public class AuthTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    TokenUtils tokenUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest)request).getHeader("Authorization");
        String korisnickoIme = tokenUtils.getUsername(token);

        if((korisnickoIme != null) && (SecurityContextHolder.getContext().getAuthentication() == null)){
            UserDetails userDetails = userDetailsService.loadUserByUsername(korisnickoIme);
            if(tokenUtils.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        super.doFilter(request, response, chain);
    }
}
