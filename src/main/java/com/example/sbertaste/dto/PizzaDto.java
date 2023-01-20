package com.example.sbertaste.dto;

import com.example.sbertaste.annotation.transfer.Exist;
import com.example.sbertaste.annotation.transfer.New;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
public class PizzaDto extends CommonDto {

    @NotBlank(message = "cannot be blank", groups = {New.class, Exist.class})
    private String name;
    @Min(value = 0, message = "cannot be negative", groups = {New.class, Exist.class})
    private int price;


}
