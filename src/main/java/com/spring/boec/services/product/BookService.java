package com.spring.boec.services.product;

import com.spring.boec.dtos.BookDTO;
import com.spring.boec.model.user.Author;
import com.spring.boec.model.product.Book;
import com.spring.boec.model.product.Publisher;
import com.spring.boec.services.BaseService;
import com.spring.boec.utils.Helper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
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
        List<BookDTO> bookDTOS = books.stream().map(modelMapper::convertToBookDTO).collect(Collectors.toList());
        bookDTOS = bookDTOS.stream().sorted(Comparator.comparingDouble(BookDTO::getAvgRating).reversed()).collect(Collectors.toList());
        return bookDTOS;
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
        List<Float> rateList = new ArrayList<>();
        Book book = bookRepository.findById(bookId).orElse(null);
        if (Objects.nonNull(book)){
            book.getRatings().forEach(p -> rateList.add(p.getRate()));
            float calculateRating = Helper.calculateRating(rateList);
            BookDTO bookDTO = modelMapper.convertToBookDTO(book);
            bookDTO.setAvgRating(calculateRating);
            return bookDTO;
        }else
            return null;
    }

}
