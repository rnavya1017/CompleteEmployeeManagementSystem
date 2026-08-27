package com.example.CompleteEmpManagementSystem.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // Secret key is read from application.properties
    @Value("${jwt.secret}")
    private String secret;

    // Token expiration time is read from application.properties
    @Value("${jwt.expiration}")
    private long expiration;

    // Creates the SecretKey from the configured secret
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    // Generates a JWT token for the given username
    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()

                // Stores username inside the JWT subject
                .subject(userDetails.getUsername())

                // Stores the time when the token was created
                .issuedAt(new Date())

                // Sets the expiration time of the token
                .expiration(
                        new Date(System.currentTimeMillis() + expiration)
                )

                // Signs the token using our secret key
                .signWith(getSecretKey())

                // Converts everything into the final JWT string
                .compact();
    }

    // Extracts the username from the JWT
    public String extractUserName(String token) {

        return Jwts.parser()

                // Verifies the token using our secret key
                .verifyWith(getSecretKey())

                .build()

                // Parses and validates the signed JWT
                .parseSignedClaims(token)

                // Gets the JWT payload
                .getPayload()

                // Gets the username stored in subject
                .getSubject();
    }

    // Checks whether the token belongs to the given user
    // and whether the token has expired
    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        String username = extractUserName(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    // Checks whether the JWT expiration time has passed
    private boolean isTokenExpired(String token) {

        Date expirationDate = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();

        return expirationDate.before(new Date());
    }
}