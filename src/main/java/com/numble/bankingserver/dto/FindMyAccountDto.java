package com.numble.bankingserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindMyAccountDto {

    public String loginId;
    public String password;

    @Builder
    public FindMyAccountDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

}
