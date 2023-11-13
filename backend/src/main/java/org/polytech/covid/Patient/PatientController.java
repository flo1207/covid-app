package org.polytech.covid.Patient;


import java.util.List;

import org.polytech.covid.Patient.files.patient;
import org.polytech.covid.Patient.service.patientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class patientController {
    
    @Autowired
    private patientService patientService;

    @GetMapping(path  = "api/private/centers/patients")
    public List<patient> getPatients(
        @RequestParam("nom") String nom){
        
        String new_nom = "%"+nom+"%";
        
        if(nom == null) return patientService.findAllByNom("%");
        else return patientService.findAllByNom(new_nom);
    }

    @PatchMapping(path  = "api/private/centers/patient")
    @ResponseBody
    public patient updatePatientVaccination(@RequestParam("id") String id){
        Long id_pat = Long.parseLong(id);
        patient patient = patientService.findByIdPatient(id_pat);
        patient.setVaccination(true); 
        return patientService.update(patient);
    }
}
