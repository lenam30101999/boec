package com.spring.boec.services;

import com.spring.boec.dtos.BookDTO;
import com.spring.boec.entities.Author;
import com.spring.boec.entities.Book;
import com.spring.boec.entities.Publisher;
import com.spring.boec.mapper.ModelMapper;
import com.spring.boec.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookService extends BaseService {

    public BookDTO addBook(BookDTO bookDTO){
        Author author = Author.builder()
                .id(bookDTO.getAuthor().getId())
                .build();
        Publisher publisher = Publisher.builder()
                .id(bookDTO.getPublisher().getId())
                .build();
        Book book = Book.builder()
                .name(bookDTO.getName())
                .author(author)
                .publisher(publisher)
                .pageCount(bookDTO.getPageCount())
                .build();
        book.setStock(bookDTO.getStock());
        book.setUrlImage(bookDTO.getUrlImage());
        book.setPrice(bookDTO.getPrice());

        bookRepository.save(book);
        return modelMapper.convertToBookDTO(book);
    }

    public List<BookDTO> getListBook(String textSearch){
        List<Book> books = bookRepository.findAllBook(textSearch.trim());
        return modelMapper.convertToListBook(books);
    }

    public BookDTO updateBook(BookDTO bookDTO){
        Book book = bookRepository.findById(bookDTO.getId()).orElse(null);
        Author author = Author.builder()
                .id(bookDTO.getAuthor().getId())
                .build();
        Publisher publisher = Publisher.builder()
                .id(bookDTO.getPublisher().getId())
                .build();
        if (Objects.nonNull(book)){
            book.setName(bookDTO.getName());
            book.setUrlImage(bookDTO.getUrlImage());
            book.setPageCount(bookDTO.getPageCount());
            book.setAuthor(author);
            book.setPublisher(publisher);
            bookRepository.saveAndFlush(book);
        }
        return modelMapper.convertToBookDTO(book);
    }

    public List<BookDTO> getAllBook(){
        List<Book> books = bookRepository.findAll();
        return books.stream().map(modelMapper::convertToBookDTO).collect(Collectors.toList());
    }

    public BookDTO deleteBookDTO(int bookId){
        Book book  = bookRepository.findById(bookId).orElse(null);
        if (Objects.nonNull(book)){
            bookRepository.delete(book);
            return modelMapper.convertToBookDTO(book);
        }else
            return null;
    }

    public BookDTO getBookDTO(int bookId){
        Book book = bookRepository.findById(bookId).orElse(null);
        if (Objects.nonNull(book)){
            return modelMapper.convertToBookDTO(book);
        }else
            return null;
    }

}
