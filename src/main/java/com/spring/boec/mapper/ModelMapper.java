package com.spring.boec.mapper;

import com.spring.boec.dtos.UserDTO;
import com.spring.boec.entities.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ModelMapper {

  @Mappings({
  })
  UserDTO convertToUserDTO(Account account);
}
