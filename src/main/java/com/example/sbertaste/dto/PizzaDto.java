package com.example.sbertaste.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PizzaDto extends CommonDto {
    private Integer id;
    private String name;
    private int price;
}
