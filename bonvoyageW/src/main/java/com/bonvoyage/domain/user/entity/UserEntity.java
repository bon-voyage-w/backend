package com.bonvoyage.domain.user.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String pw;
    private String email;
    private String name;
    private Date birth;
    private String phone;
    private boolean auth;
    private boolean avail;

    @Builder
    public UserEntity(String loginId, String pw, String email, String name, Date birth, String phone, boolean authorization, boolean avail) {
        this.loginId = loginId;
        this.pw = pw;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.auth = authorization;
        this.avail = avail;
    }
}
