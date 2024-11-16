package com.example.product.repo;

import com.example.product.model.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo extends JpaRepository<product, Integer> {
    @Query(value = "select * from  product where product_id = ?1", nativeQuery = true)
    product getproductById(Integer productId);
}
