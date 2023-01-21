package com.example.sbertaste.dto;

import com.example.sbertaste.model.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDtoWithCustomer extends OrderRequestDto {

    private CustomerEntity customer;
}
