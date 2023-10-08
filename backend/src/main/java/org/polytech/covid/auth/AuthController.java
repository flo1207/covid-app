package org.polytech.covid.auth;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;import java.util.stream.Collectors;

import org.polytech.covid.User.files.User;
import org.polytech.covid.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class AuthController {

    @Value("${jwt.secret}")
    private String jwtSecret; 
       
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(path = "api/public/login")
    @ResponseBody
    public ResponseEntity<JwtResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        

        try {
            // Effectuez l'authentification en utilisant Spring Security

            final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password,Collections.singletonList(new SimpleGrantedAuthority(   "ADMIN")))
            );

            System.out.println(authentication);

            // Générez le token JWT
            final String token = generateJwtToken(authentication);
            System.out.println(token);
            JwtResponse jwtResponse = new JwtResponse(token);

            // Retournez le token au client sous forme de réponse JSON
            return ResponseEntity.ok(jwtResponse);
        } catch (AuthenticationException e) {
            // Gérez l'erreur d'authentification ici
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN); // Réponse 401 Unauthorized
        }

        
    }



    private String generateJwtToken(org.springframework.security.core.Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        long jwtExpirationMs = 60 * 10 * 1000 ;

        // Créez une liste de rôles que l'utilisateur a
        List<String> userRoles = Arrays.asList("ADMIN");


        // Ajoutez le claim personnalisé "roles" au token
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                // .claim("roles", userRoles) // Ajoutez les rôles en tant que claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .compact();
    }


}
