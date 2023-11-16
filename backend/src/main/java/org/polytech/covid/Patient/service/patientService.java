package org.polytech.covid.Patient.service;

import java.util.List;

import org.polytech.covid.Patient.files.patient;
import org.polytech.covid.Patient.files.patientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class patientService {

    @Autowired
    private patientRepository patientRepository;
    
    public List<patient> findAllByNom(String nom){
        return patientRepository.findAllByNomLike(nom);
    }

    public patient save(patient patient){
        return patientRepository.save(patient);
    }

    public patient findByIdPatient(Long id_pat) {
        return patientRepository.findByIdPatient(id_pat);
    }

    public patient update(patient patient) {
        return patientRepository.save(patient);
    }
    
}
