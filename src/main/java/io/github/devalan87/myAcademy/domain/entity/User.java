package io.github.devalan87.myAcademy.domain.entity;

import io.github.devalan87.myAcademy.api.controller.dto.CredentialsDTO;
import io.github.devalan87.myAcademy.api.controller.dto.UserDTO;
import io.github.devalan87.myAcademy.domain.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity @Table(name = "tb_user")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType type = UserType.USER;

    @Getter(AccessLevel.PRIVATE)
    private Boolean enabled = true;
    public Boolean isEnabled() {
        return enabled;
    }

    public UserDTO dto() {
        return new UserDTO(
                username,
                email,
                type,
                enabled
        );
    }

    public CredentialsDTO getCredentials() {
        return new CredentialsDTO(
                username,
                password
        );
    }

    public CredentialsDTO setCredentials(CredentialsDTO dto) {
        username = dto.getLogin();
        password = dto.getPassword();

        return getCredentials();
    }

    public UserDetails getDetails() {
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .password(password)
                .roles(type.name())
                .disabled(!isEnabled())
                .build();
    }

}
