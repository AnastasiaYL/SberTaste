package com.example.sbertaste.dto.pizza;

import com.example.sbertaste.dto.CommonRequestDto;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class PizzaRequestDto extends CommonRequestDto {

    @NotBlank(message = "Cannot be blank")
    private String name;
    @Min(value = 0, message = "Cannot be negative")
    private int price;


}
