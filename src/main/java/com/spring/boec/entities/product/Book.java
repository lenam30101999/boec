package com.spring.boec.entities.product;


import com.spring.boec.entities.order.Rating;
import com.spring.boec.entities.orderItem.OrderItem;
import com.spring.boec.entities.user.Author;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@Builder
public class Book extends Item {

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

    @OneToMany(mappedBy = "book")
    private List<OrderItem> orderItem;

    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "book")
    private List<ProductFavorite> productFavorites;
}
