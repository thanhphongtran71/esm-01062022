package com.stdio.esm.repository;

import com.stdio.esm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Account,Long> {
    Optional<Account> findByUsername(String name);
}
