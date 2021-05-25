package com.spring.boec.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicDTO {

    private int id;

    private int power;

    private String name;

    private ManuFacturerDTO manufacturer;

    private PublisherDTO publisher;
}
