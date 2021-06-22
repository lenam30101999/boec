package com.spring.boec.controllers;

import com.spring.boec.dtos.ElectronicDTO;
import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.services.product.ElectronicService;
import com.spring.boec.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/electronics")
public class ElectronicController {

    @Autowired
    private ElectronicService electronicService;

    @CrossOrigin(origins = "*")
    @GetMapping(params = "textSearch")
    private ResponseEntity<?> getAllElectronicBySearch(@RequestParam("textSearch") String textSearch){
        List<ElectronicDTO> electronicDTOList = electronicService.getListElectronic(textSearch);
        return new ResponseEntity<>(electronicDTOList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    private ResponseEntity<?> getAllElectronic(){
        List<ElectronicDTO> electronicDTOList = electronicService.getAllElectronic();
        return new ResponseEntity<>(electronicDTOList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/add-electronic")
    private ResponseEntity<?> addElectronic(@RequestBody ElectronicDTO ElectronicDTO){
        ElectronicDTO electronicDTO1 = electronicService.addElectronic(ElectronicDTO);
        if (electronicDTO1 != null){
            return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.CREATED);
        }else return new ResponseEntity<>(new MessageDTO(Util.INSERT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update-electronic")
    private ResponseEntity<?> updateElectronic(@RequestBody ElectronicDTO ElectronicDTO){
        ElectronicDTO electronicDTO1 = electronicService.updateElectronic((ElectronicDTO));
        return new ResponseEntity<>(electronicDTO1, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete-electronic/{electronicId}")
    private ResponseEntity<?> deleteElectronic(@PathVariable("electronicId") int ElectronicId){
        ElectronicDTO electronicDTO = electronicService.deleteElectronicDTO(ElectronicId);
        return new ResponseEntity<>(electronicDTO, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get-electronic/{electronicId}")
    private ResponseEntity<?> getElectronic(@PathVariable("electronicId") int ElectronicId){
        ElectronicDTO electronicDTO = electronicService.getElectronicDTO(ElectronicId);
        if (Objects.nonNull(electronicDTO)) {
            return new ResponseEntity<>(electronicDTO, HttpStatus.OK);
        }else
            return new ResponseEntity<>(new MessageDTO(Util.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
