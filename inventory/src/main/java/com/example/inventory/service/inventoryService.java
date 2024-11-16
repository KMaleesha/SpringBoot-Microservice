package com.example.inventory.service;

import com.example.inventory.dto.inventoryDto;
import com.example.inventory.model.inventory;
import com.example.inventory.repo.inventoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class inventoryService {

    @Autowired
    private inventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<inventoryDto> getAllInventories() {
        List<inventory> inventoryList = inventoryRepo.findAll();
        return modelMapper.map(inventoryList, new TypeToken<List<inventoryDto>>() {}.getType());
    }

    public inventoryDto saveInventory(inventoryDto inventoryDto) {
        inventoryRepo.save(modelMapper.map(inventoryDto, inventory.class));
        return inventoryDto;
    }

    public inventoryDto updateInventory(inventoryDto inventoryDto) {
        inventoryRepo.save(modelMapper.map(inventoryDto, inventory.class));
        return inventoryDto;
    }

    public String deleteInventory(int id) {
        inventoryRepo.deleteById(id);
        return "Inventory deleted";
    }

    public inventoryDto getinventoryById(int id) {
        return modelMapper.map(inventoryRepo.findById(id).get(), inventoryDto.class);
    }

    public inventoryDto getItemById(Integer itemId) {
        inventory Inventory = inventoryRepo.getItemById(itemId);
        return modelMapper.map(Inventory, inventoryDto.class);
    }
}
