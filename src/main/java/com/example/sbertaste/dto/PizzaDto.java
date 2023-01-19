package com.example.sbertaste.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class PizzaDto extends CommonDto {

    private String name;
    private int price;

    public PizzaDto(Integer id, String name, int price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}
