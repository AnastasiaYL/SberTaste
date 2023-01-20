package com.example.sbertaste.controller.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SberTasteError {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> details;

    public SberTasteError(String message) {
        this.message = message;
    }
}
