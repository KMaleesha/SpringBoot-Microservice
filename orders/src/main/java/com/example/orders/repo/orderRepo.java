package com.example.orders.repo;

import com.example.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderRepo extends JpaRepository<Orders, Integer> {
}
