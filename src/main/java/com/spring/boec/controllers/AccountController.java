package com.spring.boec.controllers;

import com.spring.boec.dtos.AccountDTO;
import com.spring.boec.dtos.MessageDTO;
import com.spring.boec.services.AccountService;
import com.spring.boec.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @PostMapping(path = "/login")
  public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) {
    String messageError = accountService.checkLogin(accountDTO);
    AccountDTO data = accountService.userLogin(accountDTO.getUsername(), accountDTO.getPassword());
    String messageError2 = accountService.checkAfterLogin(data);

    if (messageError == null || messageError2 == null){
      return new ResponseEntity<>(data, HttpStatus.OK);
    }else return new ResponseEntity<>(new MessageDTO(messageError), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(path = "/signup")
  public ResponseEntity<?> signup(@RequestBody AccountDTO accountDTO) {
    AccountDTO data = accountService.signup(accountDTO);

    if (data != null){
      return new ResponseEntity<>(data, HttpStatus.OK);
    }else return new ResponseEntity<>(new MessageDTO(Util.INSERT_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
  }
}
