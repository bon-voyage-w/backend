package com.bonvoyage.domain.user.service;

import com.bonvoyage.domain.user.dto.KakaoTokenDto;
import com.bonvoyage.domain.user.dto.KakaoUserInfoDto;
import com.bonvoyage.domain.user.dto.UserDto;
import com.bonvoyage.domain.user.entity.OAuthInfoEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    boolean isRegisterUser(Long id);
    public List<UserDto> findUserList();

    int registerUser(KakaoUserInfoDto userInfoDto);
    int registerUser(UserDto userDto);
    String updateUserDetail(int userId,UserDto userDto);

    void registerOauthInfo(int userId, long oauthId, KakaoTokenDto.Response kakaoToken, OAuthInfoEntity.OAuth2 type);

    int getUserIdByOauth(KakaoUserInfoDto userInfoDto) throws Exception;

    Map<String, String> setTokenInfo(int userId);
    UserDto findUserByLoginId(String loginId);

    boolean isAuthAvail(Map<String, String> loginInfo) throws Exception;
    boolean withdrawalUser(int userId);

    int getUserIdByLoginId(String id);
    Map<String,String> getLoginIdAndNameByUserId(int userId);

    UserDto findUserByUserId(int userId);

    void removeUserRefreshToken(int userId);
    boolean isAuthorizedAdmin(int userId);
}
