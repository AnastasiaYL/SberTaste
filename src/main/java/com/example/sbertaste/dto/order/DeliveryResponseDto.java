package com.example.sbertaste.dto.order;

import com.example.sbertaste.dto.CommonResponseDto;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseDto extends CommonResponseDto {
    private String type;
    private Integer cost;
    private Integer minimalCartForFreeDelivery;

}
