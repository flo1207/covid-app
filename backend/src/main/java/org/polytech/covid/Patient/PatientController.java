package org.polytech.covid.Patient;


import java.time.LocalDate;
import java.util.List;

import org.polytech.covid.Patient.files.Patient;
import org.polytech.covid.Patient.service.PatientService;
import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;
import org.polytech.covid.VaccinationCenter.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    
    @Autowired
    private PatientService patientService;

    @GetMapping(path  = "api/centers/patients")
    public List<Patient> getPatients(
        @RequestParam("nom") String nom){
        
        String new_nom = "%"+nom+"%";
        
        if(nom == null) return patientService.findAllByNom("%");
        else return patientService.findAllByNom(new_nom);
    }


    @PostMapping(path  = "api/centers/patient")
    @ResponseBody
    public Patient addPatient(@RequestParam("mail") String mail, @RequestParam("num_tel") Integer num_tel,@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("nom_centre") String nom_centre, @RequestParam("prenom") LocalDate date) { 
        Patient new_patient = new Patient(mail,num_tel,nom,prenom,nom_centre,date);
        return patientService.saveAll(new_patient);
    }
}
