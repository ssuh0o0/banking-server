package com.numble.bankingserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SendMoneyDto {

    public String fromAccount;

    public String fromPassword;

    public String toAccount;

    public Long money;

    @Builder
    public SendMoneyDto(String fromAccount, String fromPassword, String toAccount, Long money) {
        this.fromAccount = fromAccount;
        this.fromPassword = fromPassword;
        this.toAccount = toAccount;
        this.money = money;
    }

}
