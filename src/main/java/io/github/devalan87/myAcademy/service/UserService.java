package io.github.devalan87.myAcademy.service;

import io.github.devalan87.myAcademy.domain.entity.User;
import io.github.devalan87.myAcademy.exception.UserAlreadyRegisteredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService
        extends UserDetailsService {

    User saveUser(User user) throws UserAlreadyRegisteredException;
    User findUser(String login);
    UserDetails authenticate(User user);

}
