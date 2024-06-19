package com.example.demo.persistance.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration; // Durée d'expiration en secondes

    // Générer le token JWT avec expiration
    public String generateToken(String username, String role, Long clubId) {
        // Utilisation de Keys.secretKeyFor pour générer une clé sécurisée HS512
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Calculer la date d'expiration
        Date expirationDate = new Date(System.currentTimeMillis() + expiration * 1000); // Date actuelle + expiration en ms

        // Construction du token JWT
        String token = Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("clubId", clubId)
                .setExpiration(expirationDate) // Définir l'expiration du token
                .signWith(secretKey)
                .compact();

        return token;
    }
}
