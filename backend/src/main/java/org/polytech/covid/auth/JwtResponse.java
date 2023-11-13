package org.polytech.covid.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class jwtResponse {

    private final String token;
    
    @Value("${jwt.secret}")
    private String jwtSecret;

    public jwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    
}
