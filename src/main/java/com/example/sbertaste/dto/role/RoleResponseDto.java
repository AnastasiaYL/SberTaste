package com.example.sbertaste.dto.role;

import com.example.sbertaste.dto.CommonResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleResponseDto extends CommonResponseDto {
    private String title;
    private String description;
}
