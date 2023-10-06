package org.polytech.covid.Patient;


import java.time.LocalDate;
import java.util.List;

import org.polytech.covid.Patient.files.Patient;
import org.polytech.covid.Patient.service.PatientService;
import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;
import org.polytech.covid.VaccinationCenter.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    
    @Autowired
    private PatientService patientService;

    @GetMapping(path  = "api/private/centers/patients")
    public List<Patient> getPatients(
        @RequestParam("nom") String nom){
        
        String new_nom = "%"+nom+"%";
        
        if(nom == null) return patientService.findAllByNom("%");
        else return patientService.findAllByNom(new_nom);
    }

    @PatchMapping(path  = "api/private/centers/patient")
    @ResponseBody
    public Patient updatePatientVaccination(@RequestParam("id") String id){
        Long id_pat = Long.parseLong(id);
        Patient patient = patientService.findByIdPatient(id_pat);
        patient.setVaccination(true); 
        return patientService.save(patient);
    }
}
