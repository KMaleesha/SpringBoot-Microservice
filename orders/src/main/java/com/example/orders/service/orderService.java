package com.example.orders.service;

import com.example.inventory.dto.inventoryDto;
import com.example.orders.common.ErrorOrderResponse;
import com.example.orders.common.OrderResponse;
import com.example.orders.common.SuccessOrderResponse;
import com.example.orders.dto.orderDto;
import com.example.orders.model.Orders;
import com.example.orders.repo.orderRepo;
import com.example.product.dto.productDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class orderService {

    @Autowired
    private orderRepo orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    public orderService(WebClient inventoryWebClient, WebClient productWebClient, orderRepo orderRepository, ModelMapper modelMapper) {
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public List<orderDto> getAllOrders() {
        List<Orders> ordersList = orderRepository.findAll();
        return modelMapper.map(ordersList, new TypeToken<List<orderDto>>() {}.getType());
    }

    public OrderResponse saveOrder(orderDto orderDto) {

        Integer itemId = orderDto.getItemId();

        try{
            inventoryDto inventoryResponse = inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/item/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(inventoryDto.class)
                    .block();

            assert inventoryResponse != null;

            Integer productId = inventoryResponse.getProductId();
            productDto productResponse = productWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getbyproduct/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(productDto.class)
                    .block();

            System.out.println(inventoryResponse);

            assert productResponse != null;

            if(inventoryResponse.getQuantity() > 0){
                if(productResponse.getForSale() == 1){
                    orderRepository.save(modelMapper.map(orderDto, Orders.class));
                }else{
                    return new ErrorOrderResponse("This item is not for sale");
                }
                return new SuccessOrderResponse(orderDto);
            }
            else {
                return new ErrorOrderResponse("Item Not not available");
            }

        }catch (WebClientResponseException e){
            if(e.getStatusCode().is5xxServerError()){
                return new ErrorOrderResponse(" Item Not Found");
            }
        }
        return null;
    }

    public orderDto updateOrder(orderDto orderDto) {
        orderRepository.save(modelMapper.map(orderDto, Orders.class));
        return orderDto;
    }

    public String DeleteOrder(int id) {
        orderRepository.deleteById(id);
        return "Order deleted";
    }

    public orderDto getOrder(int id) {
        return modelMapper.map(orderRepository.findById(id).get(), orderDto.class);
    }
}
