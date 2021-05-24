package com.spring.boec.services;

import com.spring.boec.dtos.AccountDTO;
import com.spring.boec.entities.Account;
import com.spring.boec.mapper.ModelMapper;
import com.spring.boec.repositories.AccountRepository;
import com.spring.boec.utils.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Log4j2
@Service
@Transactional
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ModelMapper modelMapper;

  public String checkLogin(AccountDTO accountDTO){
    if (accountDTO.getUsername().equals("")) {
      return Util.FILL_USERNAME;
    }
    if (accountDTO.getPassword().equals("")){
      return Util.FILL_PASSWORD;
    }
    if (accountDTO.getPassword().length() < 5) {
      return Util.WRONG_USERNAME_OR_PASSWORD;
    }
    return null;
  }

  public String checkAfterLogin(AccountDTO user) {
    if (Objects.isNull(user)){
      return Util.ACCOUNT_NOT_EXISTS;
    }else if (Objects.isNull(user.getPassword())){
      return Util.PASSWORD_WRONG;
    }
    return null;
  }

  public AccountDTO userLogin(String username, String password) {
    try {
      AccountDTO accountDTO;
      Account account = accountRepository.findUserByUsernameIgnoreCase(username);
      if (Objects.isNull(account)){
        return null;
      }else if (account.getPassword().equals(password)){
        accountDTO = modelMapper.convertToUserDTO(account);
        return accountDTO;
      }else {
        accountDTO = new AccountDTO();
        accountDTO.setUsername(username);
        return accountDTO;
      }
    }catch (Exception e){
      log.error(e);
      return null;
    }
  }

}
