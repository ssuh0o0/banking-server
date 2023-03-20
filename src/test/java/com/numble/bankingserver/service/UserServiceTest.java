package com.numble.bankingserver.service;

import com.numble.bankingserver.domain.User;
import com.numble.bankingserver.dto.ChangePasswordDto;
import com.numble.bankingserver.dto.JoinRequestDto;
import com.numble.bankingserver.dto.LoginRequestDto;
import com.numble.bankingserver.dto.MakeFriendDto;
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
    public void setUp() {
        userRepository.save(User.builder()
                .loginId("test")
                .password("test123")
                .username("test-name")
                .build());
    }

    @AfterEach
    public void reset() {
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
    
    @Test
    public void ChangePassword_changePassword_True() throws Exception {
        //given
        ChangePasswordDto user = ChangePasswordDto.builder()
                .loginId("test")
                .newPassword("newPassword")
                .build();

        //when
        userService.changePassword(user);
        User findUser = userRepository.findByLoginId("test").orElseThrow(NullPointerException::new);

        //then
        assertEquals(findUser.getPassword(), "newPassword");
    }
    
    @Test
    public void MakeFriend_makeFriendRelation_True() throws Exception {
        //given
        userRepository.save(User.builder()
                .loginId("test2")
                .password("test1234")
                .build());

        userRepository.save(User.builder()
                .loginId("test3")
                .password("test1234")
                .build());

        MakeFriendDto makeFriendDto = MakeFriendDto.builder()
                .from("test")
                .to("test2")
                .build();

        MakeFriendDto makeFriendDto2 = MakeFriendDto.builder()
                .from("test")
                .to("test3")
                .build();
        //when
        userService.makeFriend(makeFriendDto);
        userService.makeFriend(makeFriendDto2);
        User fromUser = userRepository.findByLoginId("test").orElseThrow(NullPointerException::new);

        //then
        assertAll(
                () -> assertEquals(fromUser.getFriendRelations().get(0).getFriend().getLoginId(), "test2"),
                () -> assertEquals(fromUser.getFriendRelations().get(1).getFriend().getLoginId(), "test3")
        );
     
    }
}