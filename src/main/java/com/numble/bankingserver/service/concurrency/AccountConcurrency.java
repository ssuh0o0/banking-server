package com.numble.bankingserver.service.concurrency;



import com.numble.bankingserver.dto.DepositDto;
import com.numble.bankingserver.dto.SendMoneyDto;
import com.numble.bankingserver.dto.WithdrawDto;
import com.numble.bankingserver.global.lock.LockManager;
import com.numble.bankingserver.service.concurrency.AccountWithLockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountConcurrency {
    private final LockManager lockManager;
    private final AccountWithLockService accountService;

    @Transactional
    public void sendWithLock(String fromAccount, String fromPassword, String toAccount, Long money) {
        SendMoneyDto sendMoneyDto = new SendMoneyDto(fromAccount, fromPassword, toAccount, money);
        lockManager.executeWithLock(
                fromAccount,
                () -> lockManager.executeWithLock(
                        toAccount,
                        () -> accountService.sendMoney(sendMoneyDto)
                )
        );
    }

    @Transactional
    public void depositWithLock(String account, Long money) {
        DepositDto depositDto = new DepositDto(account, money);

        lockManager.executeWithLock(
                account,
                () -> accountService.depositMoney(depositDto)
        );
    }

    @Transactional
    public void withdrawWithLock(String account, Long money)  {
        WithdrawDto withdrawDto = new WithdrawDto(account, money);
        lockManager.executeWithLock(
                account,
                () -> accountService.withdrawMoney(withdrawDto)
        );
    }


}