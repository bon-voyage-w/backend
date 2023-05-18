package com.bonvoyage.domain.user.client;

import com.bonvoyage.domain.user.dto.KakaoTokenDto;
import com.bonvoyage.domain.user.dto.KakaoUserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "https://kapi.kakao.com", name = "kakaoApiClient")
public interface KakaoApiClient {

    @PostMapping(value="/v2/user/me", consumes="application/json")
    KakaoUserInfoDto requestKakaoUserInfo(@RequestHeader("Content-type") String contentType, @RequestHeader("Authorization") String AccessToken);

}
