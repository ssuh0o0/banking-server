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


    public void deposit(DepositDto depositDto){
        findAccount(depositDto.account, depositDto.getLoginId()).deposit(depositDto.money);
    }

    public void withdraw(WithdrawDto withdrawDto){
        findAccount(withdrawDto.account, withdrawDto.getLoginId()).withdraw(withdrawDto.money);
    }
    

    public void sendMoney(SendMoneyDto sendMoneyDto){

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
    }

    @Transactional(readOnly=true)
    public void findMyAccount(FindMyAccountDto findMyAccountDto){
        Account myAccount = accountRepository.findAccountByAccount(findMyAccountDto.account)
                .orElseThrow(()-> new BasicException(AccountException.ACCOUNT_NOT_FOUND));

        if (myAccount.checkPassword(findMyAccountDto.accountPassword)){
            throw new BasicException(AccountException.UNAUTHORIZED_PASSWORD);
        }
    }

    private Account findAccount(String account, String loginId) {
        Account findAccount = accountRepository.findByAccount(account).orElseThrow(IllegalArgumentException::new);
        User findUser = userRepository.findByLoginId(loginId).orElseThrow(IllegalArgumentException::new);
        if (!findAccount.getUserId().equals(findUser.getId())){
            throw new IllegalStateException("본인의 계좌가 아닙니다.");
        }
        return findAccount;
    }


}
