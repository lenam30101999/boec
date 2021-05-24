package com.spring.boec.repositories;

import com.spring.boec.entities.Electronic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicRepository extends JpaRepository<Electronic, Integer> {


}
