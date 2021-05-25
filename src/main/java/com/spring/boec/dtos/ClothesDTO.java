package com.spring.boec.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClothesDTO {
  private int id;
  private String size;
  private long price;
  private int stock;
  private String name;
  private String gender;
  private PublisherDTO publisher;
}
