package com.numble.bankingserver.service;

import com.numble.bankingserver.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;


}
