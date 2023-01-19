package com.example.sbertaste.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto extends CommonDto {
    private String name;
    private String phone;
    private String email;

    public CustomerDto(Integer id, String name, String phone, String email) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
