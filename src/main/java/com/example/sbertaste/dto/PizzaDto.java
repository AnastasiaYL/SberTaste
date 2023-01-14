package com.example.sbertaste.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PizzaDto implements IDto {
    private Integer id;
    private String name;
    private double price;
}
