package com.numble.bankingserver.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class FriendRelation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "friend")
    private User friend;

    public void setFriend(User friend) {
        this.friend = friend;
    }

    @Builder
    public FriendRelation(User friend){
        this.friend = friend;
    }
}
