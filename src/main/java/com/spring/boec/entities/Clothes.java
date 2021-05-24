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

  private String size;

  private String gender;

  @JoinColumn(name = "publisher_id")
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Publisher publisher;
}
