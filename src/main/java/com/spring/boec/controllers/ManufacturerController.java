package com.spring.boec.controllers;

import com.spring.boec.dtos.AuthorDTO;
import com.spring.boec.dtos.ManuFacturerDTO;
import com.spring.boec.services.AuthorService;
import com.spring.boec.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/manufacturer/")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<?> getAllManufacturer(){
        List<ManuFacturerDTO> manuFacturerDTOS = manufacturerService.getAll();
        if (Objects.nonNull(manuFacturerDTOS)){
            return new ResponseEntity<>(manuFacturerDTOS, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
    }
}
