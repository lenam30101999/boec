package com.spring.boec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {

    private Integer id;

    private float rate;

    private String content;

    private BookDTO book;

    private ElectronicDTO electronic;

    private ClothesDTO clothes;

    private CustomerDTO customer;
}
