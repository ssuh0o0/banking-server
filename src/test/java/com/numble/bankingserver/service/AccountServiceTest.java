package com.numble.bankingserver.service;

import com.numble.bankingserver.domain.Account;
import com.numble.bankingserver.domain.User;
import com.numble.bankingserver.dto.*;
import com.numble.bankingserver.repository.AccountRepository;
import com.numble.bankingserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired AccountRepository accountRepository;
    @Autowired UserService userService;
    @Autowired AccountService accountService;

    private static final String TEST_ACCOUNT = "111-1111-111";

    @BeforeEach
    public void setUp() {
        userRepository.save(User.builder()
                .id(1L)
                .loginId("test")
                .password("test123")
                .username("test-name")
                .build());

        accountRepository.save(Account.builder()
                .userId(1L)
                .balance(10000L)
                .account(TEST_ACCOUNT)
                .build());
    }

    @AfterEach
    public void reset() {
        userRepository.deleteByLoginId("test");
        accountRepository.deleteById(1L);
    }

    @Test
    void Deposit_deposit_True() throws Exception {
        //given
        DepositDto depositDto = DepositDto.builder()
                .account("111-1111-111")
                .loginId("test")
                .money(3000L)
                .build();

        //then
        accountService.deposit(depositDto);
        Account findAccount = accountRepository.findByAccount(TEST_ACCOUNT).orElseThrow();

        //when
        assertEquals(findAccount.getBalance(), 13000L);

    }

    @Test
    void Withdraw_withdraw_True() throws Exception {

        //given
        WithdrawDto withdrawDto = WithdrawDto.builder()
                .account("111-1111-111")
                .loginId("test")
                .money(3000L)
                .build();

        //then
        accountService.withdraw(withdrawDto);
        Account findAccount = accountRepository.findByAccount(TEST_ACCOUNT).orElseThrow();

        //when
        assertEquals(findAccount.getBalance(), 7000L);
    }

    @Test
    void sendMoney() throws Exception {
        //given
        userRepository.save(User.builder()
                .id(2L)
                .loginId("test2")
                .password("test123")
                .username("test-name")
                .build());

        accountRepository.save(Account.builder()
                .userId(2L)
                .balance(10000L)
                .account("222-2222-222")
                .build());

        MakeFriendDto makeFriendDto = MakeFriendDto.builder()
                .from("test")
                .to("test2")
                .build();

        SendMoneyDto sendMoneyDto = SendMoneyDto.builder()
                .fromLoginId("test")
                .fromAccount("111-1111-111")
                .toAccount("222-2222-222")
                .money(3000L)
                .build();

        //then
        userService.makeFriend(makeFriendDto);
        accountService.sendMoney(sendMoneyDto);
        Account fromAccount = accountRepository.findByAccount(TEST_ACCOUNT).orElseThrow();
        Account toAccount = accountRepository.findByAccount("222-2222-222").orElseThrow();

        //when
        assertAll(
                () -> assertEquals(fromAccount.getBalance(), 7000L),
                () -> assertEquals(toAccount.getBalance(), 13000L)
        );
    }

    // 아직 좀 더 생각해봐야할듯..
    @Test
    void findMyAccount() throws Exception {
        //given
        FindMyAccountDto findMyAccountDto = FindMyAccountDto.builder()
                .loginId("test")
                .password("test123")
                .build();

        //when
        accountService.findMyAccount(findMyAccountDto);

        //then
    }
}