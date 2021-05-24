package com.spring.boec.dtos;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
  private int id;

  @NotNull
  private String name;

  @NotNull
  private String username;

  @NotNull
  private String password;

  private String dob;

  @NotNull
  private String phoneNo;

  @NotNull
  private String role;

  private String gender;

  private String address;

  private String email;

}
