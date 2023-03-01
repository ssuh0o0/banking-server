package com.numble.bankingserver.controller;

import com.numble.bankingserver.dto.*;
import com.numble.bankingserver.global.response.ResponseStatus;
import com.numble.bankingserver.global.response.SuccessResponse;
import com.numble.bankingserver.service.AccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping({"/deposit"})
    public SuccessResponse deposit(@Validated @RequestBody final DepositDto requestDto) {
        accountService.deposit(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(ResponseStatus.OK)
                .message("Success Deposit")
                .build();
        return res;
    }

    @PostMapping({"/withdraw"})
    public SuccessResponse withdraw(@Validated @RequestBody final WithdrawDto requestDto) {
        accountService.withdraw(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(ResponseStatus.OK)
                .message("Success Withdraw")
                .build();
        return res;
    }

    @PostMapping({"/send"})
    public SuccessResponse sendMoney(@Validated @RequestBody final SendMoneyDto requestDto) {
        accountService.sendMoney(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(ResponseStatus.OK)
                .message("Success Send Money")
                .build();
        return res;
    }

    @PostMapping({"/find"})
    public SuccessResponse findMyAccount(@Validated @RequestBody final FindMyAccountDto requestDto) {
        accountService.findMyAccount(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(ResponseStatus.OK)
                .message("Success make friend")
                .build();
        return res;
    }
}
