package com.spring.boec.controllers;

import com.spring.boec.dtos.OrderItemDTO;
import com.spring.boec.services.OrderItemService;
import com.spring.boec.utils.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<?> addOrderItem(@RequestBody OrderItemDTO orderItemDTO){
        OrderItemDTO saved = orderItemService.addOrderItem(orderItemDTO);
        if (saved != null){
            return new ResponseEntity<>(Util.ADD_SUCCESS, HttpStatus.OK);
        }else return new ResponseEntity<>(Util.ADD_NOT_SUCCESS, HttpStatus.BAD_REQUEST);
    }
}
