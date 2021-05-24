package com.spring.boec.dtos;


import com.spring.boec.entities.Author;
import com.spring.boec.entities.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private int id;

    private String name;

    private int pageCount;

    private AuthorDTO authorDTO;

    private PublisherDTO publisherDTO;

}
