package com.example.sbertaste.security;

import com.example.sbertaste.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550345465678488L;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationPeriodInMinutes}")
    private Long expirationPeriodInMinutes;

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String generateToken(UserEntity userEntity) {
        String id = UUID.randomUUID().toString().replace("-", "");
        Date expirationDate = Date.from(LocalDateTime.now().plusMinutes(expirationPeriodInMinutes).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setId(id)
                .setSubject(userEntity.getLogin())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
