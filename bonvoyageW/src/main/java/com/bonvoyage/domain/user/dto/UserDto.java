package com.bonvoyage.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Id;
import java.util.Date;

@Data
public class UserDto {
    private Long userId;

    private String loginId;
    private String pw;
    private String email;
    private String name;
    private Date birth;
    private String phone;
    private boolean auth;
    private boolean avail;

    @Builder
    public UserDto(Long userId, String loginId, String pw, String email, String name, Date birth, String phone, boolean auth, boolean avail) {
        this.userId = userId;
        this.loginId = loginId;
        this.pw = pw;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.auth = auth;
        this.avail = avail;
    }
}
