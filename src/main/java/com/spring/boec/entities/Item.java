package com.spring.boec.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item implements Serializable {
  private double price;
  private int stock;
}
