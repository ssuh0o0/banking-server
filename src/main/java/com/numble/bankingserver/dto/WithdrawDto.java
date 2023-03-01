package com.numble.bankingserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WithdrawDto {

    public String loginId;

    public String account;

    public Long money;

    @Builder
    public WithdrawDto(String loginId, String account, Long money) {
        this.loginId = loginId;
        this.account = account;
        this.money = money;
    }
}
