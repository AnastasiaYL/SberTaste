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
public class OrderPositionDto {

    //    private Integer orderId;
    private Integer pizzaId;
    private int quantity;
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPositionDto that = (OrderPositionDto) o;
        return Objects.equals(pizzaId, that.pizzaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId);
    }

    //    public OrderPositionDto(Integer id, Integer orderId, Integer pizzaId, Integer quantity, Integer price) {
//        super(id);
//        this.orderId = orderId;
//        this.pizzaId = pizzaId;
//        this.quantity = quantity;
//        this.price = price;
//    }
}
