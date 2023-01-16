package com.example.sbertaste.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto extends CommonDto {
    private Integer id;
    private String name;
    private String phone;
    private String email;
}
