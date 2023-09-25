package org.polytech.covid.VaccinationCenter.service;

import java.util.List;

import org.polytech.covid.Patient.files.Patient;
import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;
import org.polytech.covid.VaccinationCenter.files.VaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {
    
    @Autowired
    private VaccinationCentreRepository centreRepository;
    
    public List<VaccinationCentre> findAllByCity(String cityName){
        return centreRepository.findAllByCityLike(cityName);
    }

     public VaccinationCentre findByCityAndNom(String cityName, String centerNom){
        return centreRepository.findByCityAndNom(cityName,centerNom);
    }

    public VaccinationCentre saveAll(VaccinationCentre centre){
        return centreRepository.save(centre);
    }

}
