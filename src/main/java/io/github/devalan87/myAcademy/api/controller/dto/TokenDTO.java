package io.github.devalan87.myAcademy.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TokenDTO {

    private String username;
    private String token;

}
