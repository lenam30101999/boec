package com.spring.boec.repositories;

import com.spring.boec.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findUserByUsernameIgnoreCase(String username);
  User findUserById(int userId);
}
