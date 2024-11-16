package com.example.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class orderDto {

    private int id;
    private Date orderDate;
    private int amount;
    private int itemId;
}
