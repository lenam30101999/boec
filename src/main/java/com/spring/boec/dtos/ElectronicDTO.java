package com.spring.boec.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicDTO {

    private Integer id;

    private long price;

    private int stock;

    @JsonProperty("url_image")
    private String urlImage;

    private int power;

    private String name;

    private ManuFacturerDTO manufacturer;

    private PublisherDTO publisher;

    private List<RatingDTO> ratings;

    @JsonProperty("avg_rating")
    private float avgRating;
}
