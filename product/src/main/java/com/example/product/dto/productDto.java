package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class productDto {
    private int id;
    private int productId;
    private String productName;
    private String productDescription;
    private int forSale;
}