package org.polytech.covid.User;


import java.time.LocalDate;
import java.util.List;

import org.polytech.covid.User.files.User;
import org.polytech.covid.User.service.UserService;
import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;
import org.polytech.covid.VaccinationCenter.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private VaccinationCenterService centerService;


    @GetMapping(path  = "api/users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping(path  = "api/users/{id}")
    public User getUser(@PathVariable String id){
        Long convert_id = Long.parseLong(id);
        return userService.getById(convert_id);
    }

    @GetMapping(path  = "api/user/{role}")
    public User getUserRole(@PathVariable String role){ 
        Integer convert_id = Integer.parseInt(role);
        return userService.findAllByRole(convert_id);
    }

    @PostMapping(path  = "api/users")
    @ResponseBody
    public User setCenter(@RequestParam("prenom") String prenom,@RequestParam("nom") String nom,@RequestParam("password") String password, @RequestParam("mail") String mail,@RequestParam("id_center") String id_center,@RequestParam("role") String role ) { 
        Integer new_role = Integer.parseInt(role);
        Long convert_id = Long.parseLong(id_center);
        VaccinationCentre center = centerService.getById(convert_id);
        User new_user = new User(prenom,nom,password,mail,center,new_role);
                
        return userService.saveAll(new_user);


    }
}
