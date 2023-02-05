package com.example.sbertaste.dto.user;

import com.example.sbertaste.dto.CommonRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRequestDto extends CommonRequestDto {
    @NotBlank(message = "Cannot be blank")
    private String login;
    @NotBlank(message = "Cannot be blank")
    private String password;
    @NotBlank(message = "Cannot be blank")
    private String name;

    @Positive
    private Integer roleId;
}
