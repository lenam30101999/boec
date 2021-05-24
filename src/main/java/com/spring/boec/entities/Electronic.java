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
@Table(name = "electronic")
@Builder
public class Electronic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "power")
    private int power;

    @OneToOne(mappedBy = "electronic", fetch = FetchType.LAZY)
    private Manufacturer manufacturer;


}
