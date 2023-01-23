package com.example.sbertaste.dto.customer;

import com.example.sbertaste.dto.CommonResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerResponseDto extends CommonResponseDto {

    private String name;
    private String phone;
    private String email;

}
