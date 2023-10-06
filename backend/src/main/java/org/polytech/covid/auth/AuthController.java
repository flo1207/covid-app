package org.polytech.covid.auth;

import org.polytech.covid.User.files.User;
import org.polytech.covid.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {
    
    @Autowired
    private UserService userService;

    @PostMapping(path  = "api/public/login")
    @ResponseBody
    public User getUsername(@RequestParam("username") String username,@RequestParam("password") String password) { 
        User user = userService.findAllByMailAndPassword(username,password);
        if (user != null ) return user;
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }


}
