package org.polytech.covid.User;


import java.util.List;

import org.polytech.covid.User.files.User;
import org.polytech.covid.User.files.UserRepository;
import org.polytech.covid.User.service.UserService;
import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;
import org.polytech.covid.VaccinationCenter.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class UserController {
    
    @Value("${jwt.secret}")
    private String jwtSecret; 

    @Autowired
    private UserService userService;

    @Autowired
    private VaccinationCenterService centerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @GetMapping(path  = "api/private/users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping(path  = "api/users/{id}")
    public User findAllByIdUser(@PathVariable String id){
        Long convert_id = Long.parseLong(id);
        return userService.findAllByIdUser(convert_id);
    }

    @GetMapping(path  = "api/private/get/user")
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_SUPER')")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authorizationHeader){
        
        try {
            Claims claims = getClaims(authorizationHeader);

            // Vous pouvez maintenant extraire les informations du token JWT, par exemple le nom d'utilisateur
            String username = claims.getSubject();

            // Vérifiez les autorisations, par exemple si l'utilisateur a le rôle ADMIN
            if (isSuper(claims) || isAdmin(claims)) {
                User user = userService.findByMail(username);
                return ResponseEntity.ok(user);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            // En cas d'erreur de validation du JWT
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT invalide");
        }
    }




    @GetMapping(path  = "api/private/user/{role}")
    public User[] getUserRole(@PathVariable String role){ 
        SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_MDC");
        if(role.equals("1")) role_user = new SimpleGrantedAuthority("ROLE_ADMIN");
        else if(role.equals("2")) role_user = new SimpleGrantedAuthority("ROLE_SUPER");

        return userService.findAllByRole(role_user);
    }

    @GetMapping(path  = "api/private/center/users/{id}")
    public List<User> findAllByIdCentre(@PathVariable String id){ 
        Long convert_id = Long.parseLong(id);
        
        return userService.findByCenterIdCentre(convert_id);
    }

    @PostMapping(path  = "api/private/users")
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_SUPER')")
    public User setUser(@RequestHeader("Authorization") String authorizationHeader, @RequestParam("prenom") String prenom,@RequestParam("nom") String nom,@RequestParam("password") String password, @RequestParam("mail") String mail,@RequestParam("id_center") String id_center,@RequestParam("role") String role ) { 
        try {
            Claims claims = getClaims(authorizationHeader);

            // Vérifiez les autorisations, par exemple si l'utilisateur a le rôle ADMIN
            if ((isSuper(claims) && role.equals("2")) || (role.equals("1") && isAdmin(claims)) || (role.equals("0") && isAdmin(claims)) ){
                Long convert_id = Long.parseLong(id_center);
                VaccinationCentre center = centerService.findAllByIdCentre(convert_id);
                SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_MDC");
                if(role.equals("1")) role_user = new SimpleGrantedAuthority("ROLE_ADMIN");
                else if(role.equals("2")) role_user = new SimpleGrantedAuthority("ROLE_SUPER");
                
                User new_user = new User(prenom,nom,password,mail,center,role_user);

                String encodedPassword = passwordEncoder.encode(new_user.getPassword());
                new_user.setPassword(encodedPassword);

                return userRepository.save(new_user);

            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            // En cas d'erreur de validation du JWT
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT invalide");
        }
    
    }

    @PutMapping(path  = "api/private/users")
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_SUPER')")
    public User updateUser(@RequestHeader("Authorization") String authorizationHeader, @RequestParam("id") Long id,@RequestParam("prenom") String prenom,@RequestParam("nom") String nom,@RequestParam("password") String password, @RequestParam("mail") String mail,@RequestParam("id_center") Long id_center,@RequestParam("role") String role ) { 
        try {
            Claims claims = getClaims(authorizationHeader);
            // Vérifiez les autorisations, par exemple si l'utilisateur a le rôle ADMIN
            if (isSuper(claims)  || isAdmin(claims) ){
              
                VaccinationCentre center = centerService.findAllByIdCentre(id_center);
                SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_MDC");
                if(role.equals("1")) role_user = new SimpleGrantedAuthority("ROLE_ADMIN");
                else if(role.equals("2")) role_user = new SimpleGrantedAuthority("ROLE_SUPER");
                String encodedPassword = passwordEncoder.encode(password);

                User user = userService.findAllByIdUser(id);
                System.out.println(user.getMail());
                user.setMail(mail);
                user.setNom(nom);
                user.setPrenom(prenom);
                user.setPassword(encodedPassword);
                user.setRole(role_user);
                user.setCenter(center);

                return userRepository.save(user);

            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            // En cas d'erreur de validation du JWT
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT invalide");
        }
        

    }

    @DeleteMapping(value = "/api/private/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_SUPER')")
    public void deleteUser(@RequestHeader("Authorization") String authorizationHeader,@PathVariable String id) {
        try {
            Claims claims = getClaims(authorizationHeader);

            // Vérifiez les autorisations, par exemple si l'utilisateur a le rôle ADMIN
            if (isSuper(claims)  || isAdmin(claims) ){
                Long new_id = Long.parseLong(id);
                User user = userService.findAllByIdUser(new_id);
                userService.delete(user);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            // En cas d'erreur de validation du JWT
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT invalide");
        }
    }






    


    public Claims getClaims(String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");

        // Clé secrète utilisée pour signer le JWT (vous devrez configurer la clé correctement)
        String secretKey = jwtSecret;

        // Valide le token JWT
        Claims claims = Jwts.parser()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .parseClaimsJws(token)
            .getBody();
        
        return claims;
            
    }

    public Boolean isSuper(Claims claims){
        List<String> roles = (List<String>) claims.get("roles", List.class);
        if(roles != null) return roles.contains("ROLE_SUPER");
        else return false;
    }

    public Boolean isAdmin(Claims claims){
        List<String> roles = (List<String>) claims.get("roles", List.class);
        if(roles != null) return roles.contains("ROLE_ADMIN");
        else return false;
    }

    public Boolean isMdc(Claims claims){
        List<String> roles = (List<String>) claims.get("roles", List.class);
        if(roles != null) return roles.contains("ROLE_MDC");
        else return false;
    }


}
