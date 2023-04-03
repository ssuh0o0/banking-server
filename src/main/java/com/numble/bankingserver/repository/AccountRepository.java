package com.numble.bankingserver.repository;

import com.numble.bankingserver.domain.Account;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccount(String account);

    Optional<Account> findByUserId(Long userId);

    Optional<Account> findAccountByAccount(String account);

    Optional<Account> findByAccountNumberWithOptimisticLock(String account);
}
