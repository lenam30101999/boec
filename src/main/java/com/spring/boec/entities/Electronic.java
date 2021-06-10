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

    @Column(name = "power")
    private int power;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "manufacturer_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Manufacturer manufacturer;

    @JoinColumn(name = "publisher_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Publisher publisher;

    @OneToOne(mappedBy = "electronic")
    private OrderItem orderItem;
}
