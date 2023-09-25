package org.polytech.covid.Patient.service;

import java.util.List;

import org.polytech.covid.Patient.files.Patient;
import org.polytech.covid.Patient.files.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    
    public List<Patient> findAllByNom(String nom){
        return patientRepository.findAllByNomLike(nom);
    }

    public Patient saveAll(Patient patient){
        return patientRepository.save(patient);
    }
    
}
