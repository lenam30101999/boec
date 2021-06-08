package com.spring.boec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Integer id;

    private String name;

    private int pageCount;

    private AuthorDTO author;

    private PublisherDTO publisher;

    private float avgRating;

    private List<RatingDTO> ratings;
}
