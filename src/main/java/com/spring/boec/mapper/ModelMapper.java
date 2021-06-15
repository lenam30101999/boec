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
          @Mapping(target = "fullName", source = "customer.fullName"),
          @Mapping(target = "address", source = "customer.address")
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
          @Mapping(target = "ratings", source = "ratings"),
          @Mapping(target = "avgRating", ignore = true)

  })
  BookDTO convertToBookDTO(Book book);

  @Mappings({
  })
  List<BookDTO> convertToListBook(List<Book> books);

  @AfterMapping
  static void calculateRatingBook(@MappingTarget BookDTO bookDTO, Book book){
    List<Float> rateList = new ArrayList<>();
    if (book.getRatings() != null){
      book.getRatings().forEach(p -> rateList.add(p.getRate()));
      float calculate = Helper.calculateRating(rateList);
      bookDTO.setAvgRating(calculate);
    }else bookDTO.setAvgRating(0);
  }

  @Mappings({
  })
  ManuFacturerDTO convertToManufacturerDTO(Manufacturer manufacturer);

  @Mappings({
  })
  ElectronicDTO convertToElectronicDTO(Electronic electronic);

  @Mappings({})
  List<ClothesDTO> convertToListClothesDTO(List<Clothes> clothes);

  @AfterMapping
  static void calculateRatingClothes(@MappingTarget ClothesDTO clothesDTO, Clothes clothes){
    List<Float> rateList = new ArrayList<>();
    if (clothes.getRatings() != null){
      clothes.getRatings().forEach(p -> rateList.add(p.getRate()));
      float calculate = Helper.calculateRating(rateList);
      clothesDTO.setAvgRating(calculate);
    }else clothesDTO.setAvgRating(0);
  }

  @Mappings({
  })
  List<ElectronicDTO> convertListElectronic(List<Electronic> electronics);

  @AfterMapping
  static void calculateRatingElectronic(@MappingTarget ElectronicDTO electronicDTO, Electronic electronic){
    List<Float> rateList = new ArrayList<>();
    if (electronic.getRatings() != null){
      electronic.getRatings().forEach(p -> rateList.add(p.getRate()));
      float calculate = Helper.calculateRating(rateList);
      electronicDTO.setAvgRating(calculate);
    }else electronicDTO.setAvgRating(0);
  }

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
