package io.github.devalan87.myAcademy.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String errors;

    @Override
    public String toString() {
        String s = "{\n'timestamp' : " + timestamp;
        s += "\n'status' : '" + status.toString();
        s += "\n'message' : " + message;
        s += "\n'errors' : " + errors;
        return s;
    }

}
