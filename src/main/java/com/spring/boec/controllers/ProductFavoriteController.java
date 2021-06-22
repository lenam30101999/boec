package com.spring.boec.controllers;

import com.spring.boec.dtos.FavoriteDTO;
import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.services.product.ProductFavoriteService;
import com.spring.boec.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class ProductFavoriteController {
    @Autowired
    private ProductFavoriteService productFavoriteService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> addProductFavorite(@RequestBody FavoriteDTO favoriteDTO){
        FavoriteDTO saved = productFavoriteService.addFavorite(favoriteDTO);
        if (saved != null){
            return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.OK);
        }else return new ResponseEntity<>(new MessageDTO(Util.ADD_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(params = "customer_id")
    public ResponseEntity<?> getAllByCustomer(@RequestParam("customer_id") int customerId){
        List<FavoriteDTO> favoriteDTOs = productFavoriteService.getAllByCustomer(customerId);
        return new ResponseEntity<>(favoriteDTOs, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteFavoriteItem(@PathVariable("id") int id){
        FavoriteDTO favoriteDTO = productFavoriteService.deleteFavoriteItem(id);
        if (favoriteDTO != null){
            return new ResponseEntity<>(new MessageDTO(Util.DELETE_SUCCESS), HttpStatus.OK);
        }else return new ResponseEntity<>(new MessageDTO(Util.ADD_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }
}
