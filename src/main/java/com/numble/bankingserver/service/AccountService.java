package com.numble.bankingserver.service;

import com.numble.bankingserver.domain.Account;
import com.numble.bankingserver.domain.FriendRelation;
import com.numble.bankingserver.domain.User;
import com.numble.bankingserver.dto.DepositDto;
import com.numble.bankingserver.dto.FindMyAccountDto;
import com.numble.bankingserver.dto.SendMoneyDto;
import com.numble.bankingserver.dto.WithdrawDto;
import com.numble.bankingserver.global.exception.AccountException;
import com.numble.bankingserver.global.exceptionHandler.BasicException;
import com.numble.bankingserver.repository.AccountRepository;
import com.numble.bankingserver.repository.UserRepository;
import com.numble.bankingserver.service.concurrency.AccountConcurrency;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final AccountConcurrency accountConcurrency;

    /**
     * Controller에 연결되는 service 메서드
     */
    public void deposit(DepositDto depositDto){
        accountConcurrency.depositWithLock(depositDto.account, depositDto.money);
    }

    public void withdraw(WithdrawDto withdrawDto){
        accountConcurrency.withdrawWithLock(withdrawDto.account, withdrawDto.money);
    }

    public void send(SendMoneyDto sendMoneyDto){

        Account fromAccount = accountRepository.findByAccount(sendMoneyDto.fromAccount)
                .orElseThrow(()-> new BasicException(AccountException.ACCOUNT_NOT_FOUND));
        Account toAccount = accountRepository.findByAccount(sendMoneyDto.toAccount)
                .orElseThrow(()-> new BasicException(AccountException.ACCOUNT_NOT_FOUND));

        if (fromAccount.checkPassword(sendMoneyDto.fromPassword)){
            throw new BasicException(AccountException.UNAUTHORIZED_PASSWORD);
        }
        /**
         *  친구인지 확인하고 전송하는 로직 -> 락으로 트랜잭션 관리
         */
        accountConcurrency.sendWithLock(fromAccount.getAccount(), sendMoneyDto.fromPassword, toAccount.getAccount(), sendMoneyDto.money);
    }


    /**
     * Lock에서 사용하기 위한 기본 실행 메서드
     */
    public void depositMoney(DepositDto depositDto){
        findAccountWithOptimisticLock(depositDto.account).deposit(depositDto.money);
    }

    public void withdrawMoney(WithdrawDto withdrawDto){
        findAccountWithOptimisticLock(withdrawDto.account).withdraw(withdrawDto.money);
    }


    public void sendMoney(SendMoneyDto sendMoneyDto){

        Account fromAccount = findAccountWithOptimisticLock(sendMoneyDto.fromAccount);
        Account toAccount = findAccountWithOptimisticLock(sendMoneyDto.toAccount);

        if (fromAccount.checkPassword(sendMoneyDto.fromPassword)){
            throw new BasicException(AccountException.UNAUTHORIZED_PASSWORD);
        }

        if (fromAccount == toAccount){
            throw new BasicException(AccountException.MY_ACCOUNT_FAIL);
        }

        fromAccount.withdraw(sendMoneyDto.money);
        toAccount.withdraw(sendMoneyDto.money);
    }

    @Transactional(readOnly=true)
    public void findMyAccount(FindMyAccountDto findMyAccountDto){
        Account myAccount = accountRepository.findAccountByAccount(findMyAccountDto.account)
                .orElseThrow(()-> new BasicException(AccountException.ACCOUNT_NOT_FOUND));

        if (myAccount.checkPassword(findMyAccountDto.accountPassword)){
            throw new BasicException(AccountException.UNAUTHORIZED_PASSWORD);
        }
    }


    private Account findAccountWithOptimisticLock(String account) {
        return accountRepository.findByAccountNumberWithOptimisticLock(account)
                .orElseThrow(() -> new BasicException(AccountException.ACCOUNT_NOT_FOUND));
    }


}
