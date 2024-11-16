package com.example.inventory.controller;

import com.example.inventory.dto.inventoryDto;
import com.example.inventory.service.inventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="/api/v3/")
public class inventoryController {

    @Autowired
    private inventoryService inventoryService;

    @GetMapping("/getinventories")
    public List<inventoryDto> getInventory(){
        return inventoryService.getAllInventories();
    }

    @PostMapping("/saveinventory")
    public inventoryDto saveInventory(@RequestBody inventoryDto inventoryDto) {
        return inventoryService.saveInventory(inventoryDto);
    }

    @PutMapping("/updateinventory")
    public inventoryDto updateInventory(@RequestBody inventoryDto inventoryDto) {
        return inventoryService.updateInventory(inventoryDto);
    }

    @DeleteMapping("deleteinventory/{id}")
    public String deleteUser(@PathVariable int id) {
        return inventoryService.deleteInventory(id);
    }

    @GetMapping("getinventory/{id}")
    public inventoryDto getInventoryById(@PathVariable int id) {
        return inventoryService.getinventoryById(id);
    }

    @GetMapping("item/{itemId}")
    public inventoryDto getInventoryByItemId(@PathVariable Integer itemId) {
        return inventoryService.getItemById(itemId);
    }
}
