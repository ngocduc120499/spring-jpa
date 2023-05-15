package com.demoJPA.springjpa.utils;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims; import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;

@Component
public class TokenManager implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7008375124389347049L;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long REFRESH_TOKEN_VALIDITY;
    @Value("${application.security.jwt.expiration}")
    private long TOKEN_VALIDITY;
    @Value("${application.security.jwt.secret-key}")
    private String jwtSecret;

    public String generateJwtToken(UserDetails userDetails) {
        return generateJwtToken(new HashMap<>(), userDetails, TOKEN_VALIDITY);
    }
    public String refreshJwtToken(UserDetails userDetails) {
        return generateJwtToken(new HashMap<>(), userDetails, REFRESH_TOKEN_VALIDITY);
    }

    public String generateJwtToken( Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {

        Map<String, Object> claims = new HashMap<>();
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }
    public String getUsernameFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}