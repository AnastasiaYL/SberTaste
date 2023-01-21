package com.example.sbertaste.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequestDto extends CommonRequestDto {

    private Integer customerId;
    private String deliveryAddress;
    private List<Integer> orderPositionsId;
    private Double deliveryCost;
    private Date createdWhen;

//    public OrderRequestDto(Integer id, Integer customerId, String deliveryAddress, List<Integer> orderPositionsId, Double deliveryCost, Date createdWhen) {
//        super(id);
//        this.customerId = customerId;
//        this.deliveryAddress = deliveryAddress;
//        this.orderPositionsId = orderPositionsId;
//        this.deliveryCost = deliveryCost;
//        this.createdWhen = createdWhen;
//    }
}
