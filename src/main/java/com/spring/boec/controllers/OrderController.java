package com.spring.boec.controllers;

import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.dtos.OrderDTO;
import com.spring.boec.entities.Order;
import com.spring.boec.services.OrderService;
import com.spring.boec.utils.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getByCustomer(@RequestParam("customer_id") int customerId){
        OrderDTO orderDTO = orderService.getOrderByCustomer(customerId);
        if (Objects.nonNull(orderDTO)){
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        }else
            return new ResponseEntity<>(new MessageDTO(Util.CART_IS_EMPTY), HttpStatus.BAD_REQUEST);
    }
}
