package com.spring.boec.repositories;

import com.spring.boec.entities.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Integer> {
  @Query(value = " select c from Clothes c where (?1 IS NULL or lower(c.name) like lower(concat('%',concat(?1,'%'))))")
  List<Clothes> getAllClothes(String textSearch);
}
