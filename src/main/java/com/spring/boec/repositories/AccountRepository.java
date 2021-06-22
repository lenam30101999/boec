package com.spring.boec.repositories;

import com.spring.boec.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
  Account findUserByUsernameIgnoreCase(String username);
  Account findUserById(int userId);
}
