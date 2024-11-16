package com.example.inventory.repo;

import com.example.inventory.model.inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface inventoryRepo extends JpaRepository<inventory, Integer> {
    @Query(value = "SELECT * from inventory where item_id = ?1", nativeQuery = true)
    inventory getItemById(Integer itemId);
}
