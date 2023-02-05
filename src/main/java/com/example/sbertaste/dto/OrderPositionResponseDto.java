package com.example.sbertaste.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPositionResponseDto {

    //    private Integer orderId;
    private Integer pizzaId;
    private int quantity;
    private int price;
    private int amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPositionResponseDto that = (OrderPositionResponseDto) o;
        return Objects.equals(pizzaId, that.pizzaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId);
    }

}
