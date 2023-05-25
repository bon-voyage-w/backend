package com.bonvoyage.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Id;
import java.util.Date;

@Data
public class UserDto {
    private String loginId;
    private String pw;
    private String email;
    private String name;
    private Date birth;
    private String ageRange;
    private String authorization;
    private String profileImg;

    @Builder
    public UserDto( String pw, String loginId,String email, Date birth,String name, String ageRange,String authorization, String profileImg) {
        this.pw=pw;
        this.loginId = loginId;
        this.email = email;
        this.name = name;
        this.birth=birth;
        this.ageRange = ageRange;
        this.authorization = authorization;
        this.profileImg = profileImg;
    }

}
