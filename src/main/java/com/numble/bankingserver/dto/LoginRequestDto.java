package com.numble.bankingserver.dto;

import com.numble.bankingserver.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginRequestDto {

    @NotBlank(message = "ID를 입력해주세요")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;


    @Builder
    public LoginRequestDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public User toEntity(){
        return User.builder()
                .loginId(loginId)
                .password(password)
                .build();
    }
}
