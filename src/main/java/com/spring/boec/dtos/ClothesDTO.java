package com.spring.boec.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClothesDTO {
  private Integer id;

  private String size;

  private long price;

  private int stock;

  @JsonProperty("url_image")
  private String urlImage;

  private String name;

  private String gender;

  private PublisherDTO publisher;
}
