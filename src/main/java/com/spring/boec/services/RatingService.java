package com.spring.boec.services;

import com.spring.boec.dtos.RatingDTO;
import com.spring.boec.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class RatingService extends BaseService{

    public RatingDTO addRatingAndComment(RatingDTO ratingDTO){
        Customer customer = customerRepository.findById(ratingDTO.getCustomer().getId()).orElse(null);
        Rating rating = getRating(ratingDTO, customer);
        if (Objects.nonNull(customer) && Objects.nonNull(rating)){
            rating = ratingRepository.save(rating);
            return modelMapper.convertToRatingDTO(rating);
        }
        return null;
    }

    private Rating getRating(RatingDTO ratingDTO, Customer customer){
        Rating rating;
        rating = addCommentBook(ratingDTO, customer);
        if (rating == null){
            rating = addCommentClothes(ratingDTO, customer);
        }
        if (rating == null){
            rating = addCommentElectronic(ratingDTO, customer);
        }
        return rating;
    }

    private Rating addCommentBook(RatingDTO ratingDTO, Customer customer) {
        if (Objects.nonNull(ratingDTO.getBook())){
            Book book = bookRepository.findById(ratingDTO.getBook().getId()).orElse(null);
            if (Objects.nonNull(book) && Objects.nonNull(customer)) {
                return Rating.builder()
                        .rate(ratingDTO.getRate())
                        .content(ratingDTO.getContent())
                        .customer(customer)
                        .book(book)
                        .build();
            }
        }
        return null;
    }

    private Rating addCommentClothes(RatingDTO ratingDTO, Customer customer) {
        if (Objects.nonNull(ratingDTO.getClothes())) {
            Clothes clothes = clothesRepository.findById(ratingDTO.getClothes().getId()).orElse(null);
            if (Objects.nonNull(clothes) && Objects.nonNull(customer)) {
                return Rating.builder()
                        .rate(ratingDTO.getRate())
                        .content(ratingDTO.getContent())
                        .clothes(clothes)
                        .customer(customer)
                        .build();
            }
        }
        return null;
    }

    private Rating addCommentElectronic(RatingDTO ratingDTO, Customer customer) {
        if (Objects.nonNull(ratingDTO.getElectronic())) {
            Electronic electronic = electronicRepository.findById(ratingDTO.getElectronic().getId()).orElse(null);
            if (Objects.nonNull(electronic) && Objects.nonNull(customer)) {
                return Rating.builder()
                        .rate(ratingDTO.getRate())
                        .content(ratingDTO.getContent())
                        .electronic(electronic)
                        .customer(customer)
                        .build();
            }
        }
        return null;
    }

}
