package com.example.sbertaste.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPositionRequestDto {

    private Integer pizzaId;
    private int quantity;

}
