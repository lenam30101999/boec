package com.spring.boec.entities.product;

import com.spring.boec.entities.product.Book;
import com.spring.boec.entities.product.Clothes;
import com.spring.boec.entities.product.Electronic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publisher")
@Builder
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    @OneToMany(mappedBy = "publisher")
    private List<Electronic> electronics;

    @OneToMany(mappedBy = "publisher")
    private List<Clothes> clothes;
}
