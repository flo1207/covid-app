package org.polytech.covid.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenValidator {

    @Value("${jwt.secret}") // Injectez la clé secrète depuis votre configuration
    private String jwtSecret;

    public Claims validateToken(String token) {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().build();
            return jwtParser.parseClaimsJws(token).getBody();

        } catch (ExpiredJwtException e) {
            // Le token a expiré
            // Gérez l'expiration du token ici
            System.out.println("errjuer ci");
            return null;
        } catch (Exception e) {
            // Le token est invalide pour d'autres raisons
            // Gérez les autres erreurs ici
            System.out.println("errjuer la");

            return null;
        }
    }
}
