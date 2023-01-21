package com.example.sbertaste.dto.customer;

import com.example.sbertaste.annotation.validation.PhoneNumber;
import com.example.sbertaste.dto.CommonResponseDto;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerResponseDto extends CommonResponseDto {

    @NotBlank(message = "Cannot be blank")
    private String name;
    @PhoneNumber
    private String phone;
    @Email(message = "Invalid email")
    private String email;

}
