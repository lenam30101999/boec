package com.spring.boec.dtos;

import com.spring.boec.entities.Rating;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private int stock;

    private long price;

    @JsonProperty("url_image")
    private String urlImage;

    @JsonProperty("page_count")
    private int pageCount;

    private AuthorDTO author;

    private PublisherDTO publisher;

    private List<RatingDTO> ratings;

    @JsonProperty("avg_rating")
    private float avgRating;
}
