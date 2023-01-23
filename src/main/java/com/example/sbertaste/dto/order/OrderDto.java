package com.example.sbertaste.dto.order;

import com.example.sbertaste.dto.OrderPositionResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private Integer customerId;
    private double deliveryCost;
    private double amount;
    private String deliveryAddress;
    private String phone;
    private String comment;
    private LocalDateTime createdWhen;

    private List<OrderPositionResponseDto> orderPositions;
}
