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
@Table(name = "customer")
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private FullName fullName;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private Address address;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private Account account;
}
