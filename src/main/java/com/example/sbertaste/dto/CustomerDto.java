package com.example.sbertaste.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto implements IDto {
    private Integer id;
    private String name;
    private String phone;
    private String email;
}
