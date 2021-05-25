package com.spring.boec.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "electronic")
@Builder
public class Electronic extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    protected String name;

    @Column(name = "power")
    private int power;

    @JoinColumn(name = "manufacturer_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Manufacturer manufacturer;

    @JoinColumn(name = "publisher_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Publisher publisher;
}
