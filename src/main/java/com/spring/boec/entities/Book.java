package com.spring.boec.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "page_count")
    private int pageCount;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private Author author;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private Publisher publisher;
}
