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
    private Long userId;

    private String loginId;
    private String pw;
    private String email;
    private String name;
    private Date birth;
    private boolean authorization;
    private boolean avail;
    private String refreshtoken;
    private String profileImg;
    private String ageRange;

    @Builder
    public UserEntity(Long userId, String loginId, String pw, String email, String name, Date birth, boolean authorization, boolean avail, String refreshtoken, String profileImg, String ageRange) {
        this.userId = userId;
        this.loginId = loginId;
        this.pw = pw;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.authorization = authorization;
        this.avail = avail;
        this.refreshtoken = refreshtoken;
        this.profileImg = profileImg;
        this.ageRange = ageRange;
    }
}
