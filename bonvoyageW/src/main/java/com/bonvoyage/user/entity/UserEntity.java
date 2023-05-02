package com.bonvoyage.user.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="users")
public class UserEntity {
    @Id
    private int userId;

    private int loginId;
    private String pw;
    private String email;
    private String name;
    private Date birth;
    private String phone;
    private boolean authorization;
    private boolean avail;

    @Builder
    public UserEntity(int userId, int loginId, String pw, String email, String name, Date birth, String phone, boolean authorization, boolean avail) {
        this.userId = userId;
        this.loginId = loginId;
        this.pw = pw;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.authorization = authorization;
        this.avail = avail;
    }
}
