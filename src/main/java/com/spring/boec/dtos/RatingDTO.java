package com.spring.boec.dtos;

import com.spring.boec.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO implements Serializable {

    private int id;

    private float rate;

    private String content;

    private BookDTO book;

    private ElectronicDTO electronic;

    private ClothesDTO clothes;

    private CustomerDTO customer;
}
