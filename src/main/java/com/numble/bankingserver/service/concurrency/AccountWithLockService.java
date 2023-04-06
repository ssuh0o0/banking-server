package com.numble.bankingserver.service.concurrency;

import com.numble.bankingserver.domain.Account;
import com.numble.bankingserver.dto.DepositDto;
import com.numble.bankingserver.dto.SendMoneyDto;
import com.numble.bankingserver.dto.WithdrawDto;
import com.numble.bankingserver.global.exception.AccountException;
import com.numble.bankingserver.global.exceptionHandler.BasicException;
import com.numble.bankingserver.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountWithLockService {

    private final AccountRepository accountRepository;

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

    private Account findAccountWithOptimisticLock(String account) {
        return accountRepository.findAccountByAccount(account)
                .orElseThrow(() -> new BasicException(AccountException.ACCOUNT_NOT_FOUND));
    }
}
