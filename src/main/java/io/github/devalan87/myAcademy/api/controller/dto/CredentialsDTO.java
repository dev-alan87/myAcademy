package io.github.devalan87.myAcademy.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CredentialsDTO {

    private String login;
    private String password;

}
