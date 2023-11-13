package org.polytech.covid.VaccinationCenter.service;

import java.util.List;

import org.polytech.covid.User.files.user;
import org.polytech.covid.VaccinationCenter.files.vaccinationCentre;
import org.polytech.covid.VaccinationCenter.files.vaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class vaccinationCenterService {
    
    @Autowired
    private vaccinationCentreRepository centreRepository;
    
    public List<vaccinationCentre> findAllByCity(String cityName){
        return centreRepository.findAllByCityIgnoreCaseContaining(cityName);
    }
    
    public vaccinationCentre findAllByIdCentre(Long id) {
        return centreRepository.findAllByIdCentre(id);
    }

    public vaccinationCentre saveAll(vaccinationCentre centre){
        return centreRepository.save(centre);
    }

    public void delete(vaccinationCentre center) {
        centreRepository.delete(center);;
    }
    

}
