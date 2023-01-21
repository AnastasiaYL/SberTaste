package com.example.sbertaste.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderPositionRequestDto extends CommonRequestDto {

    private Integer orderId;
    private Integer pizzaId;
    private Integer quantity;
    private Integer price;

//    public OrderPositionRequestDto(Integer id, Integer orderId, Integer pizzaId, Integer quantity, Integer price) {
//        super(id);
//        this.orderId = orderId;
//        this.pizzaId = pizzaId;
//        this.quantity = quantity;
//        this.price = price;
//    }
}
