package com.example.sbertaste.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class PizzaDto extends CommonDto {

    private String name;
    private int price;

}
