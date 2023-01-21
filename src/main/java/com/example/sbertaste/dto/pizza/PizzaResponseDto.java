package com.example.sbertaste.dto.pizza;

import com.example.sbertaste.dto.CommonResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PizzaResponseDto extends CommonResponseDto {

    private String name;

    private int price;

}
