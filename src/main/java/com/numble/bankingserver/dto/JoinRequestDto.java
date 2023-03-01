package com.numble.bankingserver.dto;

import com.numble.bankingserver.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JoinRequestDto {


    private String username;

    @NotBlank(message = "ID를 입력해주세요")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;


    @Builder
    public JoinRequestDto(String username, String loginId, String password) {
        this.username = username;
        this.loginId = loginId;
        this.password = password;

    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .loginId(loginId)
                .password(password)
                .build();
    }
}
