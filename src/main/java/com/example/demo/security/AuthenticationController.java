package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/api/public/login")
    public void login(@RequestParam("name") String username,
                      @RequestParam("password") String password,
                      HttpServletResponse response) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        //Authentication authenticated = authenticationManager.authenticate(authentication);
        //SecurityContextHolder.getContext().setAuthentication(authenticated);

        //---
        final String secretKey = "myKeysjdhfgjsgfjhsdgfjsgdjfssdkfgjhsdkfjghsdfghsdfhglksdhfgklsdhgflkhsdklfghklshdfgklsdjhfggsjdgfjsg"; // Replace with your own secret key

        Date now = new Date();

        String jwtToken =  Jwts.builder()
                .setSubject(username)
                // You can add additional claims as needed
                .setExpiration(new Date(now.getTime() + 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}
