package com.spring.boec.controllers;

import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.dtos.OrderDTO;
import com.spring.boec.services.OrderService;
import com.spring.boec.utils.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired private OrderService orderService;

    @CrossOrigin(origins = "*")
    @GetMapping(params = "customer_id")
    public ResponseEntity<?> getByCustomer(@RequestParam("customer_id") int customerId){
        OrderDTO orderDTO = orderService.getOrderByCustomer(customerId);
        if (Objects.nonNull(orderDTO)){
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        }else
            return new ResponseEntity<>(new MessageDTO(Util.CART_IS_EMPTY), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<?> updateState(@RequestBody OrderDTO orderDTO){
        OrderDTO orderDTO1 = orderService.updateState(orderDTO);
        if (Objects.nonNull(orderDTO1)){
            return new ResponseEntity<>(new MessageDTO(Util.UPDATED_SUCCESS), HttpStatus.OK);
        }else
            return new ResponseEntity<>(new MessageDTO(Util.UPDATED_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    public ResponseEntity<?> findAllOrderPending(){
        List<OrderDTO> orderDTOS = orderService.findAllOrderPending();
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllOrder(){
        List<OrderDTO> orderDTOS = orderService.findAllOrder();
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }
}
