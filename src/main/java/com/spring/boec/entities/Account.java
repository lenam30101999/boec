package com.spring.boec.entities;

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
@Table(name = "user")
@Builder
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "Username", nullable = false, unique = true)
  private String username;

  @Column(name = "Password", nullable = false)
  private String password;

  @Column(name = "phoneNo", unique = true)
  private String phoneNo;

  @Column(name = "role", nullable = false)
  private String role;

  private String gender;

//  private String address;
//
//  private String email;

  @MapsId
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "id")
  private Customer customer;

}
