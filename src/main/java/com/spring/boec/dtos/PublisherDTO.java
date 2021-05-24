package com.spring.boec.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDTO {

    private int id;

    private String name;

    private String address;
}
