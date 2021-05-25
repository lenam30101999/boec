package com.spring.boec.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item implements Serializable {

  @Column(name = "price")
  protected double price;

  @Column(name = "stock")
  protected int stock;
}
