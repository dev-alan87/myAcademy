package io.github.devalan87.myAcademy.api.controller;

import io.github.devalan87.myAcademy.api.controller.dto.CredentialsDTO;
import io.github.devalan87.myAcademy.api.controller.dto.TokenDTO;
import io.github.devalan87.myAcademy.api.controller.dto.UserDTO;
import io.github.devalan87.myAcademy.domain.entity.User;
import io.github.devalan87.myAcademy.exception.UserAlreadyRegisteredException;
import io.github.devalan87.myAcademy.security.jwt.service.JwtService;
import io.github.devalan87.myAcademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO signUp(@RequestBody User user) {
        try {
            return userService.saveUser(user).dto();
        } catch (Throwable t) {
            throw new UserAlreadyRegisteredException(t.getMessage());
        }
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO signIn(@RequestBody CredentialsDTO credentials) {
        User requestUser = User.builder()
                .username(credentials.getLogin())
                .password(credentials.getPassword())
                .build();
        UserDetails authenticatedUser = userService.authenticate(requestUser);
        String token = jwtService.generateToken(authenticatedUser.getUsername());

        return new TokenDTO(authenticatedUser.getUsername(), token);
    }

}
