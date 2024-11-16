package com.example.orders.common;

public class ErrorOrderResponse implements OrderResponse{

    private final String errorMessage;

    public ErrorOrderResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
