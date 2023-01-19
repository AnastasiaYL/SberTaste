package com.example.sbertaste.dto;

import com.example.sbertaste.annotation.transfer.Exist;
import com.example.sbertaste.annotation.transfer.New;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class CommonDto {

    @Null(groups = {New.class}, message = "The ID field mustn't be passed when creating")
    @NotNull(groups = {Exist.class}, message = "The ID field must be set when updating")
    private Integer id;
}
