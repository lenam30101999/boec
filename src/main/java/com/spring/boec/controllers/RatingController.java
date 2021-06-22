package com.spring.boec.controllers;

import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.dtos.RatingDTO;
import com.spring.boec.services.order.RatingService;
import com.spring.boec.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> addRatingAndComment(@RequestBody RatingDTO ratingDTO){
        RatingDTO ratingDTO1 = ratingService.addRatingAndComment(ratingDTO);

        if (Objects.nonNull(ratingDTO1)){
            return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageDTO(Util.INSERT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }
}
