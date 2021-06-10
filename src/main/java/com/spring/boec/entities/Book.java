package com.spring.boec.entities;


import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@Builder
public class Book extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "page_count")
    private int pageCount;

    @JoinColumn(name = "author_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Author author;

    @JoinColumn(name = "publisher_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Publisher publisher;

    @OneToOne(mappedBy = "book")
    private OrderItem orderItem;
}
