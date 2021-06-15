package com.spring.boec.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`")
@Builder
@ToString
public class Order implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "date")
  private LocalDateTime date;

  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItems;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @OneToOne(mappedBy = "order")
  private Payment payment;

  @Column(name = "state")
  private String state;
}
