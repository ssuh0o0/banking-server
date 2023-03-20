package com.numble.bankingserver.controller;

import com.numble.bankingserver.dto.ChangePasswordDto;
import com.numble.bankingserver.dto.JoinRequestDto;
import com.numble.bankingserver.dto.LoginRequestDto;
import com.numble.bankingserver.dto.MakeFriendDto;
import com.numble.bankingserver.global.response.ResponseStatus;
import com.numble.bankingserver.global.response.SuccessResponse;
import com.numble.bankingserver.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping({"/join"})
    public SuccessResponse join(@Validated @RequestBody final JoinRequestDto requestDto) {
        userService.join(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(ResponseStatus.OK)
                .message("Success Join")
                .build();
        return res;
    }

    @PostMapping({"/login"})
    public SuccessResponse login(@Validated @RequestBody final LoginRequestDto requestDto) {
        userService.login(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(ResponseStatus.OK)
                .message("Success Login")
                .build();
        return res;
    }

    @PostMapping({"/change-password"})
    public SuccessResponse changePassword(@Validated @RequestBody final ChangePasswordDto requestDto) {
        userService.changePassword(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(ResponseStatus.OK)
                .message("Success Change Password")
                .build();
        return res;
    }

    @PostMapping({"/make-friend"})
    public SuccessResponse makeFriend(@Validated @RequestBody final MakeFriendDto requestDto) {
        userService.makeFriend(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(ResponseStatus.OK)
                .message("Success make friend")
                .build();
        return res;
    }
}
