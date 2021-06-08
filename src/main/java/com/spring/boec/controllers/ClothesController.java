package com.spring.boec.controllers;

import com.spring.boec.dtos.ClothesDTO;
import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.services.ClothesService;
import com.spring.boec.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/clothes")
public class ClothesController {

  @Autowired
  private ClothesService clothesService;

  @GetMapping()
  private ResponseEntity<?> getAllClothes(@RequestParam("textSearch") String textSearch){
    List<ClothesDTO> clothesDTOList  = clothesService.getListClothes(textSearch);
    return new ResponseEntity<>(clothesDTOList, HttpStatus.OK);
  }

  @PostMapping("/add-clothes")
  private ResponseEntity<?> addClothes(@RequestBody ClothesDTO clothesDTO){
    ClothesDTO clothesDTO1 = clothesService.addClothes(clothesDTO);

    if (clothesDTO1 != null){
      return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.CREATED);
    }else {
      return new ResponseEntity<>(new MessageDTO(Util.INSERT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/update-clothes")
  private ResponseEntity<?> updateClothes(@RequestBody ClothesDTO clothesDTO){
    ClothesDTO clothesDTO1 = clothesService.updateClothes((clothesDTO));
    return new ResponseEntity<>(clothesDTO1, HttpStatus.OK);
  }

  @DeleteMapping("/delete-clothes/{clothesId}")
  private ResponseEntity<?> deleteClothes(@PathVariable("clothesId") int clothesId){
    ClothesDTO clothesDTO = clothesService.deleteClothesDTO(clothesId);
    return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
  }

  @GetMapping("/get-clothes/{clothesId}")
  private ResponseEntity<?> getClothes(@PathVariable("clothesId") int clothesId){
    ClothesDTO clothesDTO = clothesService.getClothesDTO(clothesId);
    if (Objects.nonNull(clothesDTO)) {
      return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
  }
}
