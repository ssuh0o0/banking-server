package com.numble.bankingserver.domain;

import jakarta.persistence.*;
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
    @JoinColumn(name = "from_friend_id")
    private User fromFriendId;

    @ManyToOne
    @JoinColumn(name = "to_friend_id")
    private User toFriendId;

    public void setFromFriendId(User from) {
        this.fromFriendId = from;
    }

    public void setToFriendId(User to) {
        this.toFriendId = to;
    }
}
