package com.spring.boec.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullNameDTO {
  private String firstName;
  private String middleName;
  private String lastName;
}
