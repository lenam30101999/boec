package com.spring.boec.mapper;

import com.spring.boec.dtos.AccountDTO;
import com.spring.boec.entities.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ModelMapper {

  @Mappings({
  })
  AccountDTO convertToUserDTO(Account account);
}
