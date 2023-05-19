package com.bonvoyage.domain.user.service;

import com.bonvoyage.domain.user.dto.KakaoTokenDto;
import com.bonvoyage.domain.user.dto.KakaoUserInfoDto;
import com.bonvoyage.domain.user.dto.UserDto;

import java.util.List;

public interface UserService {
    boolean isRegisterUser(Long id);
    public List<UserDto> findUserList(Object o);

    int registerUser(KakaoUserInfoDto userInfoDto);

    void registerOauthInfo(int userId,long oauthId, KakaoTokenDto.Response kakaoToken);

    int getUserIdByOauth(KakaoUserInfoDto userInfoDto) throws Exception;
}
