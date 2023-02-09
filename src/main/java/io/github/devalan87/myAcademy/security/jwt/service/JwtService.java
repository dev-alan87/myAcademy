package io.github.devalan87.myAcademy.security.jwt.service;

import io.github.devalan87.myAcademy.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    String generateToken(String username);
    Boolean isValidToken(String token);
    String getUsername(String token);

}
