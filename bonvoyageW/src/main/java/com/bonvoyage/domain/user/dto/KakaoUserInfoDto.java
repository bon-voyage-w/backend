package com.bonvoyage.domain.user.dto;

import lombok.Data;
import org.hibernate.annotations.common.util.StringHelper;

@Data
public class KakaoUserInfoDto {

    Long id;

    KakaoAccount kakao_account;
    @Data
    public static class KakaoAccount{

        Profile profile;
        @Data
        public static class Profile{
            String nickname;
            String profile_image_url;
        }
        String name;
        String email;
        String birthyear;
        String birthday;
        String age_range;
    }

}
