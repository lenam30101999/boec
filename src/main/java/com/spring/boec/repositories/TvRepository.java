package com.spring.boec.repositories;

import com.spring.boec.entities.TV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvRepository extends JpaRepository<TV, Integer> {
}
