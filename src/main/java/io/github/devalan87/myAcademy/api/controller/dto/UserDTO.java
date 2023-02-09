package io.github.devalan87.myAcademy.api.controller.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    @Getter(AccessLevel.PRIVATE)
    private Boolean admin;
    @Getter(AccessLevel.PRIVATE)
    private Boolean enabled;

    public Boolean isAdmin() {
        return admin;
    }
    public Boolean isEnabled() {
        return enabled;
    }

}
