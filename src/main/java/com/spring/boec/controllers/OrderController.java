package com.spring.boec.controllers;

import com.spring.boec.dtos.OrderDTO;
import com.spring.boec.services.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAllByCustomer(@RequestParam("customer_id") int customerId){
        List<OrderDTO> orderDTOS = orderService.getAllByCustomer(customerId);
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }
}
