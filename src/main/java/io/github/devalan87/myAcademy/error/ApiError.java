package io.github.devalan87.myAcademy.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ApiError {

    private String timestamp;
    private HttpStatus status;
    private String message;
    private String errors;

    public String toJSon() {
        String json = new Gson().toJson(this);
        return json;
    }

}
