package com.numble.bankingserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WithdrawDto {

    public String LoginId;

    public String account;

    public Long money;
}
