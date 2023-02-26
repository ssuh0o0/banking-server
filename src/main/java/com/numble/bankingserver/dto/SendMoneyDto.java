package com.numble.bankingserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SendMoneyDto {

    public String fromAccount;

    public String fromLoginId;

    public String toAccount;

    public Long money;

}
