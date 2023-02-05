package com.example.sbertaste.dto.role;

import com.example.sbertaste.dto.CommonRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleRequestDto extends CommonRequestDto {
    @NotBlank(message = "Cannot be blank")
    private String title;
    private String description;
}
