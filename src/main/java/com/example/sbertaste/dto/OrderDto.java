package com.example.sbertaste.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto extends CommonDto {

    private Integer id;
    private Integer customerId;
    private String deliveryAddress;
    //    private List<Integer> orderPositionsId;
    private Double deliveryCost;
    private Date createdWhen;

}
