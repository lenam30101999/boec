package com.spring.boec.repositories;

import com.spring.boec.entities.Book;
import com.spring.boec.entities.Electronic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectronicRepository extends JpaRepository<Electronic, Integer> {

    @Query(value = " select e from Electronic e where (?1 IS NULL or lower(e.name) like lower(concat('%',concat(?1,'%'))))")
    List<Electronic> findAllElectronic(String textSearch);

}
