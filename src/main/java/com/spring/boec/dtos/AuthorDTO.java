package com.spring.boec.dtos;

import com.spring.boec.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    private int id;

    private String name;
}
