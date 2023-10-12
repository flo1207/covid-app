package org.polytech.covid.User;


import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.polytech.covid.User.files.User;
import org.polytech.covid.User.files.UserRepository;
import org.polytech.covid.User.service.UserService;
import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;
import org.polytech.covid.VaccinationCenter.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import liquibase.pro.packaged.de;
import liquibase.pro.packaged.le;

@RestController
public class UserController {
    
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

    @GetMapping(path  = "api/private/login/user/")
    public User getUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.findAllByMailAndPassword(username, password);
    }

    @GetMapping(path  = "api/private/get/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authorizationHeader){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);

        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
        String[] credentialParts = credentials.split(":", 2); // Divise les informations d'identification en nom d'utilisateur et mot de passe
        String username = credentialParts[0];
        String password = credentialParts[1];

        User user = userService.findByMail(username);
        return ResponseEntity.ok(user);
        // if (authentication != null && authentication.isAuthenticated() && authentication.getAuthorities().toString() == "ADMIN") {
        //     // Récupérer les rôles de l'utilisateur
        //     return ResponseEntity.ok(user);
        // }
            
        // else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
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
    @ResponseBody
    public User setCenter(@RequestParam("prenom") String prenom,@RequestParam("nom") String nom,@RequestParam("password") String password, @RequestParam("mail") String mail,@RequestParam("id_center") String id_center,@RequestParam("role") String role ) { 
        Long convert_id = Long.parseLong(id_center);
        VaccinationCentre center = centerService.findAllByIdCentre(convert_id);
        SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_MDC");
        if(role.equals("1")) role_user = new SimpleGrantedAuthority("ROLE_ADMIN");
        else if(role.equals("2")) role_user = new SimpleGrantedAuthority("ROLE_SUPER");
        
        User new_user = new User(prenom,nom,password,mail,center,role_user);

        String encodedPassword = passwordEncoder.encode(new_user.getPassword());
        new_user.setPassword(encodedPassword);

        return userRepository.save(new_user);

    }

    @PutMapping(path  = "api/private/users")
    @ResponseBody
    public User updateCenter(@RequestParam("id") Long id,@RequestParam("prenom") String prenom,@RequestParam("nom") String nom,@RequestParam("password") String password, @RequestParam("mail") String mail,@RequestParam("id_center") Long id_center,@RequestParam("role") String role ) { 
         
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

    }

    @DeleteMapping(value = "/api/private/user/{id}")
    public void deleteCenter(@PathVariable String id) {
        Long new_id = Long.parseLong(id);
        User user = userService.findAllByIdUser(new_id);
        userService.delete(user);

    }


}
