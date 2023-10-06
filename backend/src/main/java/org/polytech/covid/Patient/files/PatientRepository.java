package org.polytech.covid.Patient.files;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
    public List<Patient> findAllByNomLike(String nom);

    public Patient findByIdPatient(Long id_pat);
}
