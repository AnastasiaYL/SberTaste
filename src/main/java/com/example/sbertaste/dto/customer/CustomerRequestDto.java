package com.example.sbertaste.dto.customer;

import com.example.sbertaste.annotation.validation.PhoneNumber;
import com.example.sbertaste.dto.CommonRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerRequestDto extends CommonRequestDto {

    @NotBlank(message = "Cannot be blank")
    private String name;
    @PhoneNumber
    private String phone;
    @Email(message = "Invalid email")
    private String email;
}
