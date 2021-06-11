package com.spring.boec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Integer id;

    private String name;

    private int stock;

    private long price;

    private int pageCount;

    private AuthorDTO author;

    private PublisherDTO publisher;

}
