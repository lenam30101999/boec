package com.spring.boec.services;

import com.spring.boec.dtos.UserDTO;
import com.spring.boec.entities.User;
import com.spring.boec.mapper.ModelMapper;
import com.spring.boec.repositories.UserRepository;
import com.spring.boec.utils.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Log4j2
@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  public String checkLogin(UserDTO userDTO){
    if (userDTO.getUsername().equals("")) {
      return Util.FILL_USERNAME;
    }
    if (userDTO.getPassword().equals("")){
      return Util.FILL_PASSWORD;
    }
    if (userDTO.getPassword().length() < 5) {
      return Util.WRONG_USERNAME_OR_PASSWORD;
    }
    return null;
  }

  public String checkAfterLogin(UserDTO user) {
    if (Objects.isNull(user)){
      return Util.ACCOUNT_NOT_EXISTS;
    }else if (Objects.isNull(user.getPassword())){
      return Util.PASSWORD_WRONG;
    }
    return null;
  }

  public UserDTO userLogin(String username, String password) {
    try {
      UserDTO userDTO;
      User user = userRepository.findUserByUsernameIgnoreCase(username);
      if (Objects.isNull(user)){
        return null;
      }else if (user.getPassword().equals(password)){
        userDTO = modelMapper.convertToUserDTO(user);
        return userDTO;
      }else {
        userDTO = new UserDTO();
        userDTO.setUsername(username);
        return userDTO;
      }
    }catch (Exception e){
      log.error(e);
      return null;
    }
  }

}
