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

  @CrossOrigin(origins = "*")
  @GetMapping(params = "textSearch")
  private ResponseEntity<?> getAllClothesBySearch(@RequestParam("textSearch") String textSearch){
    List<ClothesDTO> clothesDTOList = clothesService.getListClothes(textSearch);
    return new ResponseEntity<>(clothesDTOList, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping()
  private ResponseEntity<?> getAllClothes(){
    List<ClothesDTO> clothesDTOList = clothesService.getAllClothes();
    return new ResponseEntity<>(clothesDTOList, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/add-clothes")
  private ResponseEntity<?> addClothes(@RequestBody ClothesDTO clothesDTO){
    ClothesDTO clothesDTO1 = clothesService.addClothes(clothesDTO);

    if (clothesDTO1 != null){
      return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.CREATED);
    }else {
      return new ResponseEntity<>(new MessageDTO(Util.INSERT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }
  }

  @CrossOrigin(origins = "*")
  @PutMapping("/update-clothes")
  private ResponseEntity<?> updateClothes(@RequestBody ClothesDTO clothesDTO){
    ClothesDTO clothesDTO1 = clothesService.updateClothes((clothesDTO));
    return new ResponseEntity<>(clothesDTO1, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping("/delete-clothes/{clothesId}")
  private ResponseEntity<?> deleteClothes(@PathVariable("clothesId") int clothesId){
    ClothesDTO clothesDTO = clothesService.deleteClothesDTO(clothesId);
    return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/get-clothes/{clothesId}")
  private ResponseEntity<?> getClothes(@PathVariable("clothesId") int clothesId){
    ClothesDTO clothesDTO = clothesService.getClothesDTO(clothesId);
    if (Objects.nonNull(clothesDTO)) {
      return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }else
      return new ResponseEntity<>(new MessageDTO(Util.NOT_FOUND), HttpStatus.NOT_FOUND);
  }
}
