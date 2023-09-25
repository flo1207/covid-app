package org.polytech.covid.VaccinationCenter;

import java.time.LocalDate;
import java.util.List;

import javax.websocket.server.PathParam;

import org.polytech.covid.Patient.files.Patient;
import org.polytech.covid.Patient.service.PatientService;
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

    @GetMapping(path  = "api/centers")
    public List<VaccinationCentre> getCity(
        @RequestParam("city") String city){
        
        String new_city = "%"+city+"%";
        
        if(city == null) return centerService.findAllByCity("%");
        else return centerService.findAllByCity(new_city);
    }

    @GetMapping(path  = "api/center")
    public VaccinationCentre getCenter(
        @RequestParam("city") String city, @RequestParam("name") String name){        
        return centerService.findByCityAndName(city,name);
    }

    @GetMapping(path  = "api/centers/detail/{id}")
    public VaccinationCentre getCenter(@PathVariable String id){ 
        Long convert_id = Long.parseLong(id);
        return centerService.getById(convert_id);
    }

    @PostMapping(path  = "api/centers")
    @ResponseBody
    public VaccinationCentre setCenter(@RequestParam("name") String name, @RequestParam("address") String address,@RequestParam("city") String city ) { 
        VaccinationCentre new_centre = new VaccinationCentre(name,address,city);
        return centerService.saveAll(new_centre);
    }

    @PostMapping(path  = "api/centers/patients")
    @ResponseBody
    public VaccinationCentre addPatient(@RequestParam("mail") String mail, @RequestParam("num_tel") Integer num_tel,@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("nom_centre") String nom_centre,@RequestParam("city") String city, @RequestParam(required = false,name="localDate") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate localDate) { 
        VaccinationCentre vaccinationCenter = centerService.findByCityAndName(city,nom_centre);
        if(vaccinationCenter == null) return null;
        else{
            
            Patient new_patient = new Patient(mail,num_tel,nom,prenom,nom_centre,localDate);

            vaccinationCenter.addPatient(new_patient);
            patientService.saveAll(new_patient);
            return centerService.saveAll(vaccinationCenter);
        }
    }

}
