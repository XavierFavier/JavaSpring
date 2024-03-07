package com.example.demo.security;

import com.example.demo.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    static final private String secretKey = "myKeysjdhfgjsgfjhsdgfjsgdjfssdkfgjhsdkfjghsdfghsdfhglksdhfgklsdhgflkhsdklfghklshdfgklsdjhfggsjdgfjsg"; // Replace with your own secret key

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            username = claimsJws.getBody().getSubject();

            System.out.println("User found.");
        } else {
            System.out.println("No header");
        }

        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();

            //
            UserDetails userDetails = new UserDTO(UUID.randomUUID(), claims.getSubject());


            final UsernamePasswordAuthenticationToken

                    authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails == null ?

                    List.of() : userDetails.getAuthorities()

            );
            // Ajoute les informations de l’utilisateur
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Met à jour le contexte d’authentification
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("logged!");
        }

        //
        filterChain.doFilter(request, response);
    }
}
