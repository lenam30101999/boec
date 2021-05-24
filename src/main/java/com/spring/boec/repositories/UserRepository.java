package com.spring.boec.repositories;

import com.spring.boec.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Integer> {
  Account findUserByUsernameIgnoreCase(String username);
  Account findUserById(int userId);
}
