package com.numble.bankingserver.repository;

import com.numble.bankingserver.domain.Account;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccount(String account);

    Optional<Account> findByLoginId(String loginId);
}
