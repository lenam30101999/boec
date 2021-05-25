package com.spring.boec.mapper;

import com.spring.boec.dtos.*;
import com.spring.boec.entities.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {

  @Mappings({})
  FullNameDTO convertToFullNameDTO(FullName fullName);

  @Mappings({})
  AddressDTO convertToAddressDTO(Address address);

  @Mappings({
  })
  AccountDTO convertToUserDTO(Account account);

  @Mappings({
  })
  AuthorDTO convertToAuthorDTO(Author author);

  @Mappings({
  })
  PublisherDTO convertToPublisherDTO(Publisher publisher);

  @Mappings({
  })
  BookDTO convertToBookDTO(Book book);

  @Mappings({
  })
  List<BookDTO> convertToListBook(List<Book> books);

  @Mappings({
  })
  ManuFacturerDTO convertToManufacturerDTO(Manufacturer manufacturer);

  @Mappings({
  })
  ElectronicDTO convertToElectronicDTO(Electronic electronic);

  @Mappings({
  })
  List<ElectronicDTO> convertListElectronic(List<Electronic> electronics);

}
