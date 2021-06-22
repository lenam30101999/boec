package com.spring.boec.repositories;

import com.spring.boec.entities.user.FullName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FullNameRepository extends JpaRepository<FullName, Integer> {
}
