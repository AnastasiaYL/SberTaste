package com.example.sbertaste.dto;

import com.example.sbertaste.model.OrderPositionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDtoWithCustomerAndPositions extends OrderRequestDtoWithCustomer {

    private List<OrderPositionEntity> orderPositions;
}
