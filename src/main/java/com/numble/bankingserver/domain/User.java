package com.numble.bankingserver.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id @GeneratedValue
    @Column(name="id")
    private Long id;

    private String username;

    private String loginId;

    private String password;

    @OneToMany(mappedBy = "fromFriendId")
    private List<FriendRelation> fromFriend = new ArrayList<>();

    @OneToMany(mappedBy = "toFriendId")
    private List<FriendRelation> toFriend = new ArrayList<>();

    @Builder
    public User(Long id, String loginId, String password, String username){
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.username = username;
    }

}
