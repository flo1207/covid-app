package org.polytech.covid.VaccinationCenter;

import java.util.List;

import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;
import org.polytech.covid.VaccinationCenter.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccinationCenterController {
    
    @Autowired
    private VaccinationCenterService centerService;

    @GetMapping(path  = "api/centers")
    public List<VaccinationCentre> getCity(
        @RequestParam("city") String city){
        if(city == null) return centerService.findAll();
        else return centerService.findAllByCity(city);
    }

    //post + save

}
