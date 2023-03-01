package com.numble.bankingserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private Long fromAccount;

    @NotBlank
    private Long toAccount;

    @NotBlank
    private Long money;

    @NotBlank
    private Long fromBalance;

    @Enumerated(EnumType.STRING)
    private HistoryType type;

    @Builder
    public History(Long fromAccount, Long toAccount, Long money, Long fromBalance, HistoryType type){
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.money = money;
        this.fromBalance = fromBalance;
        this.type = type;
    }


}
