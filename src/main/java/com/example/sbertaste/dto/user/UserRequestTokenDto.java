package com.example.sbertaste.dto.user;

import com.example.sbertaste.dto.CommonRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRequestTokenDto extends CommonRequestDto {
    @NotBlank(message = "Cannot be blank")
    private String login;
    @NotBlank(message = "Cannot be blank")
    private String password;
}
