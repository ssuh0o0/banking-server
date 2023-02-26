package com.numble.bankingserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindMyAccountDto {

    public String loginId;

    public String password;

}
