package com.spring.boec.services;

import com.spring.boec.dtos.RatingDTO;
import com.spring.boec.entities.*;
import com.spring.boec.mapper.ModelMapper;
import com.spring.boec.repositories.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@Log4j2
public class RatingService {

    @Autowired
    private RatingRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ElectronicRepository electronicRepository;

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    public RatingDTO addCommentBook(RatingDTO ratingDTO) {
        Book book = bookRepository.findById(ratingDTO.getBook().getId()).orElse(null);
        Customer customer  = customerRepository.findById(ratingDTO.getCustomer().getId()).orElse(null);
        if (Objects.nonNull(book) && Objects.nonNull(customer)) {
            Rating rating = Rating.builder()
                    .rate(ratingDTO.getRate())
                    .content(ratingDTO.getContent())
                    .book(book)
                    .customer(customer)
                    .build();
            repository.save(rating);
            return modelMapper.convertToRatingDTO(rating);
        }
        return null;
    }

    public RatingDTO addCommentClothes(RatingDTO ratingDTO) {
        Clothes clothes = clothesRepository.findById(ratingDTO.getClothes().getId()).orElse(null);
        Customer customer  = customerRepository.findById(ratingDTO.getCustomer().getId()).orElse(null);
        if (Objects.nonNull(clothes) && Objects.nonNull(customer)) {
            Rating rating = Rating.builder()
                .rate(ratingDTO.getRate())
                .content(ratingDTO.getContent())
                .clothes(clothes)
                .customer(customer)
                .build();
        repository.save(rating);
        return modelMapper.convertToRatingDTO(rating);
    }
        return null;
}

    public RatingDTO addCommentElectronic(RatingDTO ratingDTO) {
        Electronic electronic = electronicRepository.findById(ratingDTO.getElectronic().getId()).orElse(null);
        Customer customer  = customerRepository.findById(ratingDTO.getCustomer().getId()).orElse(null);
        if (Objects.nonNull(electronic) && Objects.nonNull(customer)) {
            Rating rating = Rating.builder()
                    .rate(ratingDTO.getRate())
                    .content(ratingDTO.getContent())
                    .electronic(electronic)
                    .customer(customer)
                    .build();
            repository.save(rating);
            return modelMapper.convertToRatingDTO(rating);
        }
        return null;
    }

}
