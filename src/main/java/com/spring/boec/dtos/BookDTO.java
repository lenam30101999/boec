package com.spring.boec.dtos;

import com.spring.boec.entities.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private List<RatingDTO> ratings;
}
