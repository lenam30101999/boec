package com.spring.boec.controllers;

import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.dtos.OrderItemDTO;
import com.spring.boec.services.orderItem.OrderItemService;
import com.spring.boec.utils.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> addOrderItem(@RequestBody OrderItemDTO orderItemDTO){
        OrderItemDTO saved = orderItemService.addOrderItem(orderItemDTO);
        if (saved != null){
            return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.OK);
        }else return new ResponseEntity<>(new MessageDTO(Util.ADD_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<?> editOrderItem(@RequestBody List<OrderItemDTO> orderItemDTOs){
        List<OrderItemDTO> orderItemDTOS = orderItemService.updateOrderItem(orderItemDTOs);
        if (orderItemDTOS.size() > 0){
            return new ResponseEntity<>(orderItemDTOS, HttpStatus.OK);
        }else return new ResponseEntity<>(new MessageDTO(Util.ADD_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable("id") int id){
        OrderItemDTO orderItemDTO = orderItemService.deleteOrderItem(id);
        if (orderItemDTO != null){
            return new ResponseEntity<>(new MessageDTO(Util.DELETE_SUCCESS), HttpStatus.OK);
        }else return new ResponseEntity<>(new MessageDTO(Util.ADD_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }
}
