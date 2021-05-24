package com.spring.boec.repositories;

import com.spring.boec.entities.TextBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextBookRepository extends JpaRepository<TextBook, Integer> {
}
