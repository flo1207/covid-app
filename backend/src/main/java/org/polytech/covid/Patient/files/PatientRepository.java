package org.polytech.covid.Patient.files;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface patientRepository extends JpaRepository<patient, Integer>{
    public List<patient> findAllByNomLike(String nom);

    public patient findByIdPatient(Long id_pat);
}
