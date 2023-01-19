package com.example.sbertaste.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends CommonDto {

    private String name;
    private String phone;
    private String email;

}
