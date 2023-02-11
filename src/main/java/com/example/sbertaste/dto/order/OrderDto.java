package com.example.sbertaste.dto.order;

import com.example.sbertaste.dto.orderPosition.OrderPositionResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer customerId;
    private int deliveryCost;
    private int amount;
    private String deliveryAddress;
    private String phone;
    private String comment;
    private LocalDateTime createdWhen;

    private List<OrderPositionResponseDto> orderPositions;
}
