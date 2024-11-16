package com.example.product.controller;

import com.example.product.dto.productDto;
import com.example.product.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="api/v2/")
public class productController {

    @Autowired
    private productService productService;

    @GetMapping("/getproducts")
    public List<productDto> getproducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getbyproduct/{productId}")
    public productDto getproduct(@PathVariable Integer productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/saveproduct")
    public productDto saveproduct(@RequestBody productDto product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/updateproduct")
    public productDto updateproduct(@RequestBody productDto product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public void deleteproduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }
}
