package com.example.sbertaste.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderPositionDto extends CommonDto {

    private Integer id;
    private Integer orderId;
    private Integer pizzaId;
    private Integer quantity;
    private Integer price;

}
