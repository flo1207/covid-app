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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccinationCenterController {
    
    @Autowired
    private VaccinationCenterService centerService;

    @Autowired
    private PatientService patientService;

    @GetMapping(path  = "api/public/centers")
    public List<VaccinationCentre> getCity(
        @RequestParam("city") String city){
        
        // String new_city = "%"+city+"%";
        
        if(city == null) return centerService.findAllByCity("%");
        else return centerService.findAllByCity(city);
    }

    @GetMapping(path  = "api/public/center")
    public VaccinationCentre getCenter(
        @RequestParam("id") Long id){        
        return centerService.findAllByIdCentre(id);
    }

    @PostMapping(path  = "api/public/centers")
    @ResponseBody
    public VaccinationCentre setCenter(@RequestParam("name") String name, @RequestParam("address") String address,@RequestParam("city") String city ) { 
        VaccinationCentre new_centre = new VaccinationCentre(name,address,city);
        return centerService.saveAll(new_centre);
    }

    @PostMapping(path  = "api/public/centers/patients")
    @ResponseBody
    public VaccinationCentre addPatient(@RequestParam("mail") String mail, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("id_centre") Long id_centre, @RequestParam(required = false,name="localDate") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate localDate) { 
        VaccinationCentre vaccinationCenter = centerService.findAllByIdCentre(id_centre);
        if(vaccinationCenter == null) return null;
        else{
            
            Patient new_patient = new Patient(mail,nom,prenom,id_centre,false,localDate);

            vaccinationCenter.addPatient(new_patient);
            patientService.save(new_patient);
            return centerService.saveAll(vaccinationCenter);
        }
    }

}
