package com.example.product.service;

import com.example.product.dto.productDto;
import com.example.product.model.product;
import com.example.product.repo.productRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class productService {

    @Autowired
    private productRepo  productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<productDto> getAllProducts() {
        List<product> productList = productRepository.findAll();
        return modelMapper.map(productList, new TypeToken<List<productDto>>() {}.getType());
    }

    public productDto saveProduct(productDto productDto) {
        productRepository.save(modelMapper.map(productDto, product.class));
        return productDto;
    }

    public productDto updateProduct(productDto productDto) {
        productRepository.save(modelMapper.map(productDto, product.class));
        return productDto;
    }

    public String deleteProduct(int productId) {
        productRepository.deleteById(productId);
        return "product deleted";
    }

    public productDto getProduct(Integer productId) {
        product Product = productRepository.getproductById(productId);
        return modelMapper.map(Product, productDto.class);
    }
}
