package com.numble.bankingserver.service;

import com.numble.bankingserver.domain.User;
import com.numble.bankingserver.dto.JoinRequestDto;
import com.numble.bankingserver.dto.LoginRequestDto;
import com.numble.bankingserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @BeforeEach
    public void setUser() {
        User user = User.builder()
                .loginId("test")
                .password("test123")
                .username("test-name")
                .build();

        userRepository.save(user);
    }

    @AfterEach
    public void resetUser() {
        userRepository.deleteByLoginId("test");
    }

    @Test
    @DisplayName("Join API :: duplicate User")
    public void Join_DuplicateUser_False() throws Exception {
        //given
        JoinRequestDto user1 = JoinRequestDto.builder()
                .loginId("test")
                .password("test123")
                .build();

        //when then
        assertThatThrownBy(() -> userService.join(user1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 가입된 유저입니다");

    }

    @Test
    @DisplayName("Login API :: cannot login")
    public void Login_NotExistUser_False() throws Exception {
        //given
        LoginRequestDto user1 = LoginRequestDto.builder()
                .loginId("NotExistUser")
                .password("test123")
                .build();

        //when then
        assertThatThrownBy(() -> userService.login(user1))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("해당 ID가 존재하지 않습니다.");

    }

    @Test
    @DisplayName("Login API :: dont match password")
    public void Login_NotMatchPassword_False() throws Exception {
        //given
        LoginRequestDto user1 = LoginRequestDto.builder()
                .loginId("test")
                .password("NotMatchPassword")
                .build();

        //when then
        assertThatThrownBy(() -> userService.login(user1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("ID와 비밀번호가 일치하지 않습니다.");

    }
}