package com.spring.boec.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
