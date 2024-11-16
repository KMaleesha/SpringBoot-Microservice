package com.example.orders.controller;

import com.example.orders.common.OrderResponse;
import com.example.orders.dto.orderDto;
import com.example.orders.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v4/")
@CrossOrigin
public class orderController {

    @Autowired
    private orderService orderService;

    @GetMapping("/getorders")
    public List<orderDto> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getbyorderid/{id}")
    public orderDto getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/saveorder")
    public OrderResponse saveOrder(@RequestBody orderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    @PutMapping("/updateorder")
    public orderDto updateOrder(@RequestBody orderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @DeleteMapping("/deleteorder/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.DeleteOrder(id);
    }
}
