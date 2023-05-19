package com.bonvoyage.domain.user.service;


import com.bonvoyage.domain.user.dto.KakaoTokenDto;
import com.bonvoyage.domain.user.dto.KakaoUserInfoDto;
import com.bonvoyage.domain.user.dto.UserDto;
import com.bonvoyage.domain.user.entity.OAuthInfoEntity;
import com.bonvoyage.domain.user.entity.UserEntity;
import com.bonvoyage.domain.user.repository.OAuthIfoRepository;
import com.bonvoyage.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OAuthIfoRepository oAuthIfoRepository;


    @Override
    public List<UserDto> findUserList(Object o) {
        List<UserEntity> userEntities=userRepository.findAll();
        List<UserDto> userDtos= new ArrayList<>();
        for (UserEntity user: userEntities) {
            UserDto userDto =UserDto.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .build();
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public int registerUser(KakaoUserInfoDto userInfoDto) {
        UserEntity userEntity = UserEntity.builder()
                .authorization(false)
                .email(userInfoDto.getKakao_account().getEmail())
                .name(userInfoDto.getKakao_account().getProfile().getNickname())
                .profileImg(userInfoDto.getKakao_account().getProfile().getProfile_image_url())
                .avail(true)
                .ageRange(userInfoDto.getKakao_account().getAge_range())
                .build();
        UserEntity user=userRepository.save(userEntity);
        return Math.toIntExact(user.getUserId());
    }
    @Override
    public void registerOauthInfo(int userId,long oauthId, KakaoTokenDto.Response kakaoToken) {
        OAuthInfoEntity oAuthInfoEntity= OAuthInfoEntity.builder()
                .accessToken(kakaoToken.getAccess_token())
                .refreshToken(kakaoToken.getRefresh_token())
                .oauthId(oauthId)
                .userId(userId)
                .build();
        oAuthIfoRepository.save(oAuthInfoEntity);
    }

    @Override
    public int getUserIdByOauth(KakaoUserInfoDto userInfoDto) throws Exception{
        Optional<OAuthInfoEntity> optionalOAuthInfoEntity=oAuthIfoRepository.findByOauthId(userInfoDto.getId());
        OAuthInfoEntity oAuthInfoEntity=optionalOAuthInfoEntity.orElse(null);
        return oAuthInfoEntity.getUserId();
    }

    @Override //해당 oauthId를 가진 유저가 이미 존재하는가
    public boolean isRegisterUser(Long oauthId) {
        return oAuthIfoRepository.existsByOauthId(oauthId);
    }
}
