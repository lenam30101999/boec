package com.spring.boec.services;

import com.spring.boec.dtos.RatingDTO;
import com.spring.boec.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class RatingService extends BaseService{

    public RatingDTO addCommentBook(RatingDTO ratingDTO) {
        Book book = bookRepository.findById(ratingDTO.getBook().getId()).orElse(null);
        Customer customer  = customerRepository.findById(ratingDTO.getCustomer().getId()).orElse(null);
        if (Objects.nonNull(book) && Objects.nonNull(customer)) {
            Rating rating = Rating.builder()
                    .rate(ratingDTO.getRate())
                    .content(ratingDTO.getContent())
                    .customer(customer)
                    .book(book)
                    .build();
            ratingRepository.save(rating);
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
        ratingRepository.save(rating);
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
            ratingRepository.save(rating);
            return modelMapper.convertToRatingDTO(rating);
        }
        return null;
    }

}
