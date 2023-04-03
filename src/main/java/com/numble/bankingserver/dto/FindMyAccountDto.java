package com.numble.bankingserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindMyAccountDto {

    public String account;
    public String accountPassword;

    @Builder
    public FindMyAccountDto(String account, String accountPassword) {
        this.account = account;
        this.accountPassword = accountPassword;
    }

}
