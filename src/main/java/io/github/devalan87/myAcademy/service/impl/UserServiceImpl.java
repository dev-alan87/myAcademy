package io.github.devalan87.myAcademy.service.impl;

import io.github.devalan87.myAcademy.domain.entity.User;
import io.github.devalan87.myAcademy.domain.repository.UserRepository;
import io.github.devalan87.myAcademy.exception.InvalidPassordException;
import io.github.devalan87.myAcademy.exception.UserAlreadyRegisteredException;
import io.github.devalan87.myAcademy.exception.UserNotFoundException;
import io.github.devalan87.myAcademy.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public User saveUser(User user)
            throws UserAlreadyRegisteredException {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User findUser(String login) {
        return repository
                .findByUsername(login)
                .orElseGet(() -> repository
                        .findByEmail(login)
                        .orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDetails authenticate(User user) {
        UserDetails details =
                loadUserByUsername(user.getUsername());
        if(encoder.matches(
                user.getPassword(),
                details.getPassword()
        ) && details.isEnabled())
            return details;
        throw new InvalidPassordException();
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return findUser(username).getDetails();
    }
}
