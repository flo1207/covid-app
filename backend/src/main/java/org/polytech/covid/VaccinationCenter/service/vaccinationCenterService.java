package org.polytech.covid.VaccinationCenter.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.polytech.covid.User.files.user;
import org.polytech.covid.VaccinationCenter.files.vaccinationCentre;
import org.polytech.covid.VaccinationCenter.files.vaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class vaccinationCenterService {
    
    @Autowired
    private vaccinationCentreRepository centreRepository;
    /**
     * On enregistre un super utilisateur au demarrage s'il n'est pas déjà enregistré
     */
    @PostConstruct
    public void createUserDefault() {
        vaccinationCentre center = centreRepository.findByName("Centre 1");
        if(center == null){
            System.out.println("Creation du centre");
            vaccinationCentre new_center = new vaccinationCentre();
            new_center.setName("Centre 1");
            new_center.setAddress("2 rue des acacias");
            new_center.setCity("Nancy");
            this.centreRepository.save(new_center);
        }
    }
    
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
