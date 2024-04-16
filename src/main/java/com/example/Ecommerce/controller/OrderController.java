package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.OrderDto;
import com.example.Ecommerce.dto.OrderSummary;
import com.example.Ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
//
//    @PostMapping
//    public ResponseEntity<?> addOrder(@RequestBody OrderDto orderRequest) {
//       //TODO
//    }
//
//    @GetMapping(path = "/get")
//    public List<OrderDto> getAllOrders(){
//        //TODO
//    }
//
//    @GetMapping(path = "/get/{id}")
//    public OrderDto getOrderById(@PathVariable Long id) {
//        //TODO
//    }
//
//    @GetMapping(path = "/{id}/summary")
//    public OrderSummary getOrderSummary(@PathVariable Long id) {
//        //TODO
//    }


}
