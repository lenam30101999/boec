package com.spring.boec.controllers;

import com.spring.boec.dtos.AccountDTO;
import com.spring.boec.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @PostMapping
  public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO){
    AccountDTO dto = accountService.userLogin(accountDTO.getUsername(), accountDTO.getPassword());
    if (dto != null){
      return new ResponseEntity<>("Đăng nhập thành công", HttpStatus.OK);
    }else return new ResponseEntity<>("Đăng nhập thất bại", HttpStatus.BAD_REQUEST);
  }
}
