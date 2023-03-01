package com.numble.bankingserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SendMoneyDto {

    public String fromAccount;

    public String fromLoginId;

    public String toAccount;

    public Long money;

    @Builder
    public SendMoneyDto(String fromAccount, String fromLoginId, String toAccount, Long money) {
        this.fromAccount = fromAccount;
        this.fromLoginId = fromLoginId;
        this.toAccount = toAccount;
        this.money = money;
    }

}
