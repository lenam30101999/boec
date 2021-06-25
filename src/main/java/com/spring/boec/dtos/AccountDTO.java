package com.spring.boec.dtos;

import com.spring.boec.annotations.PhoneNumberConstraint;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
  private int id;

  @NotNull
  private String username;

  private String password;

  @NotNull
  @PhoneNumberConstraint
  private String phoneNo;

  @NotNull
  private String role;

  private String gender;

  private FullNameDTO fullName;

  private AddressDTO address;

}
