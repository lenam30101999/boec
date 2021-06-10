package com.spring.boec.entities;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clothes")
public class Clothes extends Item{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  protected String name;

  private String size;

  private String gender;

  @JoinColumn(name = "publisher_id")
  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  private Publisher publisher;

  @OneToOne(mappedBy = "clothes")
  private OrderItem orderItem;
}
