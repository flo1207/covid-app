package org.polytech.covid.auth;

import java.util.List;import java.util.stream.Collectors;

import org.polytech.covid.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@RestController
public class AuthController {

    @Value("${jwt.secret}")
    private String jwtSecret; 

    @Autowired
    private UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping(path = "api/public/login")
    @ResponseBody
    public ResponseEntity<JwtResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // On vérifie le mot de passe fourni par l'utilisateur par rapport à celui chargé par UserDetailsService
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                String secretKey = jwtSecret;
                List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
                
                String token = Jwts.builder()
                    .setSubject(username)
                    .claim("roles", roles)
                    .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                    .compact();

                JwtResponse jwtResponse = new JwtResponse(token);
                return ResponseEntity.ok(jwtResponse);
            } else {
                // Mot de passe incorrect
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (UsernameNotFoundException e) {
            // Si l'utilisateur n'existe pas
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }


}
