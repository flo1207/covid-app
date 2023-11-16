package org.polytech.covid.auth;

import java.util.Date;
import java.util.List;import java.util.stream.Collectors;

import org.polytech.covid.User.files.user;
import org.polytech.covid.User.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@RestController
public class authController {

    @Value("${jwt.secret}")
    private String jwtSecret; 

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private userService userService;

    @Autowired
    public authController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping(path = "api/public/login")
    @ResponseBody
    public ResponseEntity<jwtResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // On vérifie le mot de passe fourni par l'utilisateur par rapport à celui chargé par UserDetailsService
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                String secretKey = jwtSecret;
                long expirationMillis = System.currentTimeMillis() + 3600000; // 1 hour in milliseconds
                Date expirationDate = new Date(expirationMillis);
                List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
                
                String token = Jwts.builder()
                    .setSubject(username)
                    .claim("roles", roles)
                    .setExpiration(expirationDate)
                    .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                    .compact();

                jwtResponse jwtResponse = new jwtResponse(token);
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

    @GetMapping(path = "api/public/test")
    @ResponseBody
    public String test() {
        user user = this.userService.findByMail("super_admin@gmail.com");
        System.out.println(user);
        return this.userService.findByMail("super_admin@gmail.com").getNom();
    }


}
