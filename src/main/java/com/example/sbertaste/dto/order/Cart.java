package com.example.sbertaste.dto.order;

import com.example.sbertaste.dto.orderPosition.OrderPositionResponseDto;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
@Data
public class Cart {

    private List<OrderPositionResponseDto> orderPositions = new ArrayList<>();

}
