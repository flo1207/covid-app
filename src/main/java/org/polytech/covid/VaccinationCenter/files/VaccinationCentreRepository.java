package org.polytech.covid.VaccinationCenter.files;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCentreRepository extends JpaRepository<VaccinationCentre, Integer>{
        public List<VaccinationCentre> findAllByCityLike(String city);
    }

