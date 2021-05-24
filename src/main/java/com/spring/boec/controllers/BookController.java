package com.spring.boec.controllers;

import com.spring.boec.dtos.BookDTO;
import com.spring.boec.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    private ResponseEntity<?> getAllBook(@RequestParam("textSearch") String textSearch){
        List<BookDTO> bookDTOList  = bookService.getListBook(textSearch);
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }
}
