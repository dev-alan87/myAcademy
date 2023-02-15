package io.github.devalan87.myAcademy.api.controller.dto;

import io.github.devalan87.myAcademy.domain.entity.enums.UserType;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    private UserType type;
    @Getter(AccessLevel.PRIVATE)
    private Boolean enabled;
    public Boolean isEnabled() {
        return enabled;
    }

}
