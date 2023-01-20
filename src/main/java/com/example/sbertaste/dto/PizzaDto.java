package com.example.sbertaste.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class PizzaDto extends CommonDto {

    @NotBlank(message = "Cannot be blank")
    private String name;
    @Min(value = 0, message = "Cannot be negative")
    private int price;

}
