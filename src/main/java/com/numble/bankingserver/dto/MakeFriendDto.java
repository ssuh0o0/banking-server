package com.numble.bankingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MakeFriendDto {

    @NotBlank
    private String fromFriendLoginId;

    @NotBlank
    private  String toFriendLoginId;

    @Builder
    public MakeFriendDto(String from, String to) {
        this.fromFriendLoginId = from;
        this.toFriendLoginId = to;
    }
}
