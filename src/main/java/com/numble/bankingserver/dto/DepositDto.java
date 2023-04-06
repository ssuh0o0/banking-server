package com.numble.bankingserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DepositDto {

    public String account;

    public Long money;

    @Builder
    public DepositDto(String account, Long money) {
        this.account = account;
        this.money = money;
    }

}
