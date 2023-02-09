package io.github.devalan87.myAcademy.security.jwt.service.impl;

import io.github.devalan87.myAcademy.domain.entity.User;
import io.github.devalan87.myAcademy.security.jwt.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class JwtServiceImpl
        implements JwtService {

    @Value("${security.jwt.signature-key}")
    private String signatureKey;

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(
                        Date.from(
                                LocalDateTime.now()
                                        .plusMinutes(Long.valueOf(expiration))
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()
                        )
                )
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    @Override
    public Boolean isValidToken(String token) {
        try {
            return !LocalDateTime.now().isAfter(
                    getClaims(token)
                            .getExpiration()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime()
            );
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJwt(token)
                .getBody();
    }

}
