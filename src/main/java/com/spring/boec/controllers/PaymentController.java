package com.spring.boec.controllers;

import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.dtos.PaymentDTO;
import com.spring.boec.services.PaymentService;
import com.spring.boec.utils.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired private PaymentService paymentService;

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> checkoutPayment(@PathVariable("id") int paymentId){
        PaymentDTO paymentDTO = paymentService.checkoutPayment(paymentId);
        if (Objects.nonNull(paymentDTO)){
            return new ResponseEntity<>(new MessageDTO(Util.CHECKOUT_SUCCESS), HttpStatus.OK);
        }else return new ResponseEntity<>(new MessageDTO(Util.CHECKOUT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }
}
