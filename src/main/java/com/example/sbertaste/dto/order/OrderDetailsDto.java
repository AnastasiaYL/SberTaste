package com.example.sbertaste.dto.order;

import com.example.sbertaste.annotation.validation.PhoneNumber;
import lombok.Data;

@Data
public class OrderDetailsDto {
    private String name;
    private String deliveryAddress;
    private int deliveryTypeId;
    @PhoneNumber
    private String phone;
    private String comment;
}
