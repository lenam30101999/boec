package com.spring.boec.controllers;

import com.spring.boec.dtos.AuthorDTO;
import com.spring.boec.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/authors/")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<?> getAllAuthor(){
        List<AuthorDTO> authorDTOS = authorService.getAllAuthor();
        if (Objects.nonNull(authorDTOS)){
            return new ResponseEntity<>(authorDTOS, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
    }
}
