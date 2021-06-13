package com.spring.boec.mapper;

import com.spring.boec.dtos.*;
import com.spring.boec.entities.*;
import com.spring.boec.utils.Helper;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {

  @Mappings({})
  PaymentDTO convertToPaymentDTO(Payment payment);

  @Mappings({
          @Mapping(target = "paymentDTO", source = "payment")
  })
  OrderDTO convertOrderDTO(Order order);

  @Mappings({
          @Mapping(target = "id", source = "id"),
          @Mapping(target = "bookDTO", source = "book"),
          @Mapping(target = "clothesDTO", source = "clothes"),
          @Mapping(target = "electronicDTO", source = "electronic")
  })
  OrderItemDTO convertOrderItemDTO(OrderItem orderItem);

  @Mappings({
  })
  ClothesDTO convertToClothesDTO(Clothes clothes);

  @Mappings({})
  FullNameDTO convertToFullNameDTO(FullName fullName);

  @Mappings({})
  AddressDTO convertToAddressDTO(Address address);

  @Mappings({
  })
  AccountDTO convertToUserDTO(Account account);

  @Mappings({
      @Mapping(target = "name", source = "name")
  })
  AuthorDTO convertToAuthorDTO(Author author);

  @Mappings({
      @Mapping(target = "name", source = "name"),
      @Mapping(target = "address", source = "address")
  })
  PublisherDTO convertToPublisherDTO(Publisher publisher);

  @Mappings({
          @Mapping(target = "price", source = "price"),
          @Mapping(target = "stock", source = "stock"),
          @Mapping(target = "avgRating", ignore = true)

  })
  BookDTO convertToBookDTO(Book book);

  @Mappings({
  })
  List<BookDTO> convertToListBook(List<Book> books);
  @AfterMapping
  static void calculateRating(@MappingTarget BookDTO bookDTO, Book book){
    List<Float> rateList = new ArrayList<>();
    book.getRatings().stream().forEach(p->rateList.add(p.getRate()));
    float calculate = Helper.calculateRating(rateList);
    bookDTO.setAvgRating(calculate);
  }

  @Mappings({
  })
  ManuFacturerDTO convertToManufacturerDTO(Manufacturer manufacturer);

  @Mappings({
  })
  ElectronicDTO convertToElectronicDTO(Electronic electronic);

  @Mappings({
  })
  List<ElectronicDTO> convertListElectronic(List<Electronic> electronics);

  @Mappings({
  })
  CustomerDTO convertToCustomerDTO(Customer customer);

  @Mappings({
  })
  List<RatingDTO> convertListRating(List<Rating> ratings);

  @Mappings({
          @Mapping(target = "book", ignore = true),
          @Mapping(target = "clothes", ignore = true),
          @Mapping(target = "electronic", ignore = true)
  })
  RatingDTO convertToRatingDTO(Rating rating);


}
