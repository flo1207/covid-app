package org.polytech.covid.VaccinationCenter.files;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface vaccinationCentreRepository extends JpaRepository<vaccinationCentre, Integer>{
    public List<vaccinationCentre> findAllByCityIgnoreCaseContaining(String city);
    public vaccinationCentre findAllByIdCentre(Long id);
}
