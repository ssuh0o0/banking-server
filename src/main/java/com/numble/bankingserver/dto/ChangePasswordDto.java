package com.numble.bankingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChangePasswordDto {
    @NotBlank(message = "ID를 입력해주세요")
    private String loginId;

    @NotBlank(message = "바꿀 비밀번호를 입력해주세요")
    private String newPassword;


    @Builder
    public ChangePasswordDto( String loginId, String newPassword) {
        this.loginId = loginId;
        this.newPassword = newPassword;
    }
}
