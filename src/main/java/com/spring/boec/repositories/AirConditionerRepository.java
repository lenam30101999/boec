package com.spring.boec.repositories;

import com.spring.boec.entities.AirConditioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirConditionerRepository extends JpaRepository<AirConditioner, Integer> {

}
