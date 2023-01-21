package com.example.sbertaste.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorPrettyView {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> details;

    public ErrorPrettyView(String message) {
        this.message = message;
    }
}
