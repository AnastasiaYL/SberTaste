package com.example.sbertaste.dto.user;

import com.example.sbertaste.dto.CommonResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserResponseDto extends CommonResponseDto {
    private String login;
//    private String password;
    private String name;
    private Integer roleId;
}
