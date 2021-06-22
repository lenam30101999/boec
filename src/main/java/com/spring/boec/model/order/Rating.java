package com.spring.boec.model.order;

import com.spring.boec.model.product.Book;
import com.spring.boec.model.product.Clothes;
import com.spring.boec.model.product.Electronic;
import com.spring.boec.model.user.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
@Builder
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "rate")
    private float rate;

    @JoinColumn(name = "customer_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Customer customer;

    @JoinColumn(name = "book_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Book book;

    @JoinColumn(name = "clothes_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Clothes clothes;

    @JoinColumn(name = "electronic_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Electronic electronic;
}
