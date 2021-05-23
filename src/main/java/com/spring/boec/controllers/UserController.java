package com.spring.boec.controllers;

import com.spring.boec.dtos.UserDTO;
import com.spring.boec.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
    UserDTO dto = userService.userLogin(userDTO.getUsername(), userDTO.getPassword());
    if (dto != null){
      return new ResponseEntity<>("Đăng nhập thành công", HttpStatus.OK);
    }else return new ResponseEntity<>("Đăng nhập thất bại", HttpStatus.BAD_REQUEST);
  }
}
