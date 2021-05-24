package com.spring.boec.mapper;

import com.spring.boec.dtos.AccountDTO;
import com.spring.boec.dtos.AuthorDTO;
import com.spring.boec.dtos.BookDTO;
import com.spring.boec.dtos.PublisherDTO;
import com.spring.boec.entities.Account;
import com.spring.boec.entities.Author;
import com.spring.boec.entities.Book;
import com.spring.boec.entities.Publisher;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {

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
          @Mapping(target = "authorDTO", source = "author"),
          @Mapping(target = "publisherDTO", source = "publisher")
  })
  BookDTO convertToBookDTO(Book book);

  @Mappings({
  })
  List<BookDTO> convertToListBook(List<Book> books);

}
