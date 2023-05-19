package com.bonvoyage.domain.user.service;

import com.bonvoyage.domain.user.dto.KakaoTokenDto;
import com.bonvoyage.domain.user.dto.KakaoUserInfoDto;
import com.bonvoyage.domain.user.dto.UserDto;
import com.bonvoyage.domain.user.entity.OAuthInfoEntity;

import java.util.List;

public interface UserService {
    boolean isRegisterUser(Long id);
    public List<UserDto> findUserList(Object o);

    int registerUser(KakaoUserInfoDto userInfoDto);

    void registerOauthInfo(int userId, long oauthId, KakaoTokenDto.Response kakaoToken, OAuthInfoEntity.OAuth2 type);

    int getUserIdByOauth(KakaoUserInfoDto userInfoDto) throws Exception;
}
