package com.company.coursya.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {

    String extractUsername(String token);

    Date extractExpirationDate(String token);

    <T> T extractClaims(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    String generateToken(String userName, String role);

    Boolean validateToken(String token, UserDetails userDetails);
}
