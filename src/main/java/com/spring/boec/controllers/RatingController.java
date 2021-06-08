package com.spring.boec.controllers;

import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.dtos.RatingDTO;
import com.spring.boec.services.RatingService;
import com.spring.boec.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/rating/")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("add-comment-book")
    public ResponseEntity<?> addCommentBook(@RequestBody RatingDTO ratingDTO){
        RatingDTO ratingDTO1 = ratingService.addCommentBook(ratingDTO);

        if (Objects.nonNull(ratingDTO1)){
            return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageDTO(Util.INSERT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("add-comment-clothes")
    public ResponseEntity<?> addCommentClothes(@RequestBody RatingDTO ratingDTO){
        RatingDTO ratingDTO1 = ratingService.addCommentClothes(ratingDTO);

        if (Objects.nonNull(ratingDTO1)){
            return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageDTO(Util.INSERT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("add-comment-electronic")
    public ResponseEntity<?> addCommentElectronic(@RequestBody RatingDTO ratingDTO){
        RatingDTO ratingDTO1 = ratingService.addCommentElectronic(ratingDTO);

        if (Objects.nonNull(ratingDTO1)){
            return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageDTO(Util.INSERT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }
}
