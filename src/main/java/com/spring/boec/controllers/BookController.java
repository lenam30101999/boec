package com.spring.boec.controllers;

import com.spring.boec.dtos.BookDTO;
import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.services.BookService;
import com.spring.boec.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @CrossOrigin(origins = "*")
    @GetMapping(params = "textSearch")
    private ResponseEntity<?> getAllBookBySearch(@RequestParam("textSearch") String textSearch){
        List<BookDTO> bookDTOList  = bookService.getListBook(textSearch);
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/add-book")
    private ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO){
        BookDTO bookDTO1 = bookService.addBook(bookDTO);

        if (bookDTO1 != null){
            return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(new MessageDTO(Util.INSERT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update-book")
    private ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO){
        BookDTO bookDTO1 = bookService.updateBook((bookDTO));
        return new ResponseEntity<>(bookDTO1, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete-book/{bookId}")
    private ResponseEntity<?> deleteBook(@PathVariable("bookId") int bookId){
        BookDTO bookDTO = bookService.deleteBookDTO(bookId);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get-book/{bookId}")
    private ResponseEntity<?> getBook(@PathVariable("bookId") int bookId){
        BookDTO bookDTO = bookService.getBookDTO(bookId);
        if (Objects.nonNull(bookDTO)) {
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    private ResponseEntity<?> getAllBook(){
        List<BookDTO> bookDTOs = bookService.getAllBook();
        return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
    }
}
