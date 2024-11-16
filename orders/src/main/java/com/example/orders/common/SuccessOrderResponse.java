package com.example.orders.common;

import com.example.orders.dto.orderDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse{

    @JsonUnwrapped
    private final orderDto order;

    public SuccessOrderResponse(orderDto order) {
        this.order = order;
    }
}
