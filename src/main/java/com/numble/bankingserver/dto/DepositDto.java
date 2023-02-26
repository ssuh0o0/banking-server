package com.numble.bankingserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DepositDto {

    public String LoginId;

    public String account;

    public Long money;

}
