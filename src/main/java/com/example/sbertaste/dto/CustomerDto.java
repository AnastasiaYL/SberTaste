package com.example.sbertaste.dto;

import com.example.sbertaste.annotation.transfer.Exist;
import com.example.sbertaste.annotation.transfer.New;
import com.example.sbertaste.annotation.validation.PhoneNumber;
import lombok.*;

import javax.validation.constraints.Email;


@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends CommonDto {

    private String name;
    @PhoneNumber(groups = {New.class, Exist.class})
    private String phone;
    @Email(message = "Invalid email field", groups = {New.class, Exist.class})
    private String email;

}
