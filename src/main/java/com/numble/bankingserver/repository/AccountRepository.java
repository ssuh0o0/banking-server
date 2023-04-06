package com.numble.bankingserver.repository;

import com.numble.bankingserver.domain.Account;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccount(String account);

    Optional<Account> findByUserId(Long userId);

    @Query("select a from Account a join fetch a.owner m where a.account= :account")
    Optional<Account> findAccountByAccount(@Param("account") String accountNumber);
}
