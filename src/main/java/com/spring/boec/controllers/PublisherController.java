package com.spring.boec.controllers;


import com.spring.boec.dtos.PublisherDTO;
import com.spring.boec.services.product.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/publishers/")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<?> getAllPublisher(){
        List<PublisherDTO> publisherDTOS = publisherService.getAll();
        if (Objects.nonNull(publisherDTOS)){
            return new ResponseEntity<>(publisherDTOS, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
    }
}
