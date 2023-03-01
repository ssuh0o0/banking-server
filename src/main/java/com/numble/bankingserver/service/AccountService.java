package com.numble.bankingserver.service;

import com.numble.bankingserver.domain.Account;
import com.numble.bankingserver.domain.FriendRelation;
import com.numble.bankingserver.domain.User;
import com.numble.bankingserver.dto.DepositDto;
import com.numble.bankingserver.dto.FindMyAccountDto;
import com.numble.bankingserver.dto.SendMoneyDto;
import com.numble.bankingserver.dto.WithdrawDto;
import com.numble.bankingserver.global.lock.LockManager;
import com.numble.bankingserver.repository.AccountRepository;
import com.numble.bankingserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    
    // 트랜잭션 원자성 보장 + 동시성 문제 해결 부분
    public void sendMoney(SendMoneyDto sendMoneyDto){

        Account fromAccount = findAccount(sendMoneyDto.fromAccount, sendMoneyDto.fromLoginId);
        Account toAccount = accountRepository.findByAccount(sendMoneyDto.toAccount).orElseThrow(IllegalArgumentException::new);

        User fromUser = userRepository.findById(fromAccount.getUserId()).orElseThrow(IllegalStateException::new);
        List<FriendRelation> friendList = fromUser.getFriendRelations();

        boolean IsFriend = false;
        for (FriendRelation friend:friendList){
            if (friend.getFriend().getId().equals(toAccount.getUserId())){
                IsFriend = true;
            }
        }

        if (IsFriend) {
            fromAccount.withdraw(sendMoneyDto.money);
            toAccount.deposit(sendMoneyDto.money);
        }
        else {
            throw new IllegalStateException("친구가 아닌 관계에서는 이체가 불가능합니다.");
        }
    }

    // 내 계좌만 조회 가능하기 위해 토큰 설정 해야함.
    // 헤더에 내 계정임을 알 수 있는 것을 보내야할 듯 ㅠ
    public void findMyAccount(FindMyAccountDto findMyAccountDto){
        User myUser = userRepository.findByLoginId(findMyAccountDto.loginId).orElseThrow(IllegalArgumentException::new);
        Account myAccount = accountRepository.findByUserId(myUser.getId()).orElseThrow(IllegalArgumentException::new);
        if (myUser.checkPassword(findMyAccountDto.password)){
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
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
