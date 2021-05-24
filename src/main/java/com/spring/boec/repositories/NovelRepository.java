package com.spring.boec.repositories;

import com.spring.boec.entities.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelRepository extends JpaRepository<Novel, Integer> {
}
