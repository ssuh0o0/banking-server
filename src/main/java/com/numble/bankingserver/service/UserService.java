package com.numble.bankingserver.service;

import com.numble.bankingserver.domain.User;
import com.numble.bankingserver.dto.JoinRequestDto;
import com.numble.bankingserver.dto.LoginRequestDto;
import com.numble.bankingserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private void duplicateUser(String loginId) {
        userRepository.findByLoginId(loginId).ifPresent(u -> {
                throw new IllegalArgumentException("이미 가입된 유저입니다");
            }
        );
    }

    public void join(JoinRequestDto joinRequestDto) {

        duplicateUser(joinRequestDto.getLoginId());
        userRepository.save(joinRequestDto.toEntity());
    }

    public void login(LoginRequestDto loginRequestDto) {
        Optional<User> findUser = userRepository.findByLoginId(loginRequestDto.getLoginId());
        if (!findUser.orElseThrow(() -> new NullPointerException("해당 ID가 존재하지 않습니다."))
                .checkPassword(loginRequestDto.getPassword())) {
            throw new IllegalStateException("ID와 비밀번호가 일치하지 않습니다.");
        }
    }
}
