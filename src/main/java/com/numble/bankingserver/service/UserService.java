package com.numble.bankingserver.service;

import com.numble.bankingserver.domain.User;
import com.numble.bankingserver.dto.JoinRequestDto;
import com.numble.bankingserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private void duplicateUser(String loginId) {
        userRepository.findByLoginId(loginId).ifPresent(u -> {
                throw new IllegalArgumentException("Duplicated User");
            }
        );
    }

    public void join(JoinRequestDto joinRequestDto) {

        duplicateUser(joinRequestDto.getLoginId());
        User user = joinRequestDto.toEntity();
        userRepository.save(user);
    }
}
