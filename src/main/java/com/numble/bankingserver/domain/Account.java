package com.numble.bankingserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;

import java.util.regex.Pattern;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private Long userId;

    @UniqueElements
    private String account;

    @Value("0")
    private Long balance;

    @Builder
    public Account(Long userId, String account, Long balance) {
        isAccount(account);

        this.userId = userId;
        this.account = account;
        this.balance = balance;
    }

    //== 객체 확인 메서드 ==/
    private static final Pattern PATTERN = Pattern.compile("([0-9,\\-]{3,6}\\-[0-9,\\-]{2,6}\\-[0-9,\\-])");

    private void isAccount(String account){
        if (!PATTERN.matcher(account).matches()) {
            throw new IllegalArgumentException("계좌번호가 잘못되었습니다");
        }
    }

    //== 비즈니스 로직 ==/
    /*
     * 계좌 입금
     */
    public void deposit(Long money){ this.balance += money; }

    /*
     * 계좌 출급
     */
    public void withdraw(Long money){
        this.balance -= money;
    }

}
