package com.spring.boec.repositories;

import com.spring.boec.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = " select b from Book b where (?1 IS NULL or lower(b.name) like lower(concat('%',concat(?1,'%'))))" +
            " or (?1 IS NULL or lower(b.author.name) like lower(concat('%',concat(?1,'%')))) ")
    List<Book> findAllBook(String textSearch);

    

}
