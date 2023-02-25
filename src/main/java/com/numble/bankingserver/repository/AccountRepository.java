package com.numble.bankingserver.repository;

import com.numble.bankingserver.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
