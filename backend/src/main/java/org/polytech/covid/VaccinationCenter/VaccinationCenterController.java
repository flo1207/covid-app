package org.polytech.covid.VaccinationCenter;

import java.time.LocalDate;
import java.util.List;

import javax.websocket.server.PathParam;

import org.polytech.covid.Patient.files.Patient;
import org.polytech.covid.Patient.service.PatientService;
import org.polytech.covid.User.files.User;
import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;
import org.polytech.covid.VaccinationCenter.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
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
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class VaccinationCenterController {

    @Value("${jwt.secret}")
    private String jwtSecret; 
    
    @Autowired
    private VaccinationCenterService centerService;

    @Autowired
    private PatientService patientService;

     private final AuthenticationManager authenticationManager;

    @Autowired
    public VaccinationCenterController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping(path  = "api/public/centers")
    public List<VaccinationCentre> getCity(@RequestParam("city") String city){        
        if(city == null) return centerService.findAllByCity("%");
        else return centerService.findAllByCity(city);
    }

    @GetMapping(path  = "api/public/center")
    public VaccinationCentre getCenter(
        @RequestParam("id") Long id){        
        return centerService.findAllByIdCentre(id);
    }

    @PostMapping(path  = "api/private/centers")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public VaccinationCentre setCenter(@RequestHeader("Authorization") String authorizationHeader, @RequestParam("name") String name, @RequestParam("address") String address,@RequestParam("city") String city ) { 
        System.out.println("######### la");
        try {

            Claims claims = getClaims(authorizationHeader);

            System.out.println(claims);

            // Vérifiez les autorisations, par exemple si l'utilisateur a le rôle ADMIN
            if (isSuper(claims) ){
                VaccinationCentre new_centre = new VaccinationCentre(name,address,city);
                return centerService.saveAll(new_centre);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            // En cas d'erreur de validation du JWT
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT invalide");
        }
    }

    @PutMapping(path  = "api/private/centers")
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public VaccinationCentre updateCenter(@RequestHeader("Authorization") String authorizationHeader, @RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("address") String address,@RequestParam("city") String city ) { 
        try {
            Claims claims = getClaims(authorizationHeader);

            // Vérifiez les autorisations, par exemple si l'utilisateur a le rôle ADMIN
            if (isSuper(claims) ){
                VaccinationCentre centre = this.centerService.findAllByIdCentre(id);
                centre.setAddress(address);
                centre.setName(name);
                centre.setCity(city);
                return centerService.saveAll(centre);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            // En cas d'erreur de validation du JWT
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT invalide");
        }
    }

    @PostMapping(path  = "api/public/centers/patients")
    @ResponseBody
    public VaccinationCentre addPatient( @RequestParam("mail") String mail, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("id_centre") Long id_centre, @RequestParam(required = false,name="localDate") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate localDate) { 
        VaccinationCentre vaccinationCenter = centerService.findAllByIdCentre(id_centre);
        if(vaccinationCenter == null) return null;
        else{
            
            Patient new_patient = new Patient(mail,nom,prenom,id_centre,false,localDate);

            vaccinationCenter.addPatient(new_patient);
            patientService.save(new_patient);
            return centerService.saveAll(vaccinationCenter);
        }
    }

    @DeleteMapping(value = "/api/private/center/{id}")
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public void deleteCenter(@RequestHeader("Authorization") String authorizationHeader,@PathVariable String id) {
        try {
            Claims claims = getClaims(authorizationHeader);

            // Vérifiez les autorisations, par exemple si l'utilisateur a le rôle ADMIN
            if (isSuper(claims) ){
                Long new_id = Long.parseLong(id);
                VaccinationCentre center = centerService.findAllByIdCentre(new_id);
                centerService.delete(center);
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

}
