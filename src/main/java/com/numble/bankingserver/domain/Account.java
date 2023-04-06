package com.numble.bankingserver.domain;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.regex.Pattern;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User owner;

    @NotBlank
    private String account;

    @NotBlank
    private String accountPassword;

    @Value("0")
    private Long balance;

    @Builder
    public Account(User owner, String account, String accountPassword, Long balance) {
        isAccount(account);

        this.owner = owner;
        this.account = account;
        this.accountPassword = accountPassword;
        this.balance = balance;
    }

    //== 객체 확인 메서드 ==/
    private static final Pattern PATTERN = Pattern.compile("[\\d\\-]+");

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
        if (this.balance - money > 0){
            this.balance -= money;
        } else{
            throw new IllegalStateException("잔액이 부족합니다.");
        }
    }

    /*
     * 계좌 비밀번호 체크
     */
    public boolean checkPassword(String accountPassword) {
        if (this.accountPassword.equals(accountPassword)) {
            return true;
        }
        return false;
    }


}
