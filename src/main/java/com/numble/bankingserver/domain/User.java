package com.numble.bankingserver.domain;


import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "friend")
    private List<FriendRelation> friendRelations = new ArrayList<>();


    @Builder
    public User(Long id, String loginId, String password, String username, FriendRelation... friendRelations){
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.username = username;
    }

    //== 연관 관계 메서드 ==/
    public void addFriendRelation(FriendRelation friendRelations){
        this.friendRelations.add(friendRelations);
    }
    public void setPassword(String password){
        this.password = password;
    }

    //== 비즈니스 로직 ==/
    public boolean checkPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }
}
