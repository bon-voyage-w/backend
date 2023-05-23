package com.bonvoyage.domain.user.service;


import com.bonvoyage.domain.user.dto.KakaoTokenDto;
import com.bonvoyage.domain.user.dto.KakaoUserInfoDto;
import com.bonvoyage.domain.user.dto.UserDto;
import com.bonvoyage.domain.user.entity.OAuthInfoEntity;
import com.bonvoyage.domain.user.entity.UserEntity;
import com.bonvoyage.domain.user.repository.OAuthIfoRepository;
import com.bonvoyage.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OAuthIfoRepository oAuthIfoRepository;
    private final JWTService jwtService;


    @Override
    public List<UserDto> findUserList() {
        List<UserEntity> userEntities=userRepository.findAll();
        List<UserDto> userDtos= new ArrayList<>();
        for (UserEntity user: userEntities) {
            UserDto userDto =UserDto.builder()
                    .loginId(user.getLoginId())
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
    public int registerUser(UserDto userDto){
        UserEntity userEntity = UserEntity.builder()
                .authorization(false)
                .email(userDto.getEmail())
                .name(userDto.getName())
                .profileImg(userDto.getProfileImg())
                .avail(true)
                .birth(userDto.getBirth())
                .ageRange("20~29")
                .build();
        UserEntity user=userRepository.save(userEntity);
        return Math.toIntExact(user.getUserId());
    }
    public String updateUserDetail(int userId, UserDto userDto){
        UserEntity userEntity= userRepository.findById((long) userId).orElseThrow(NoSuchElementException::new);
        userEntity.updateUserDetail(userDto.getLoginId(),userDto.getPw(),userDto.getEmail(),userDto.getName(),userDto.getBirth(),userDto.getProfileImg(),userDto.getAgeRange());
        return userDto.getLoginId();
    }
    @Override
    public void registerOauthInfo(int userId, long oauthId, KakaoTokenDto.Response kakaoToken, OAuthInfoEntity.OAuth2 type) {
        OAuthInfoEntity oAuthInfoEntity= OAuthInfoEntity.builder()
                .accessToken(kakaoToken.getAccess_token())
                .refreshToken(kakaoToken.getRefresh_token())
                .oauthId(oauthId)
                .userId(userId)
                .type(type)
                .build();
        oAuthIfoRepository.save(oAuthInfoEntity);
    }

    @Override
    public int getUserIdByOauth(KakaoUserInfoDto userInfoDto) throws Exception{
        Optional<OAuthInfoEntity> optionalOAuthInfoEntity=oAuthIfoRepository.findByOauthId(userInfoDto.getId());
        OAuthInfoEntity oAuthInfoEntity=optionalOAuthInfoEntity.orElse(null);
        return oAuthInfoEntity.getUserId();
    }

    @Override
    public Map<String, String> setTokenInfo(int userId) {
        Map<String,String> token= new HashMap<>();
        String accessToken=jwtService.createAccessToken("userId",userId);
        String refreshToken=jwtService.createRefreshToken("userId",userId);
        token.put("access_token",accessToken);
        token.put("refresh_token",refreshToken);

        UserEntity userEntity=userRepository.findById((long)userId)
                .orElseThrow(NoSuchElementException::new);
        userEntity.setRefreshToken(refreshToken);

        return token;
    }

    @Override //해당 oauthId를 가진 유저가 이미 존재하는가
    public boolean isRegisterUser(Long oauthId) {
        return oAuthIfoRepository.existsByOauthId(oauthId);
    }

    public UserDto findUserByLoginId(String loginId) {
        UserEntity userEntity= userRepository.findByLoginId(loginId).orElseThrow(NoSuchElementException::new);
        UserDto userDto =UserDto.builder()
                .email(userEntity.getEmail())
                .loginId(userEntity.getLoginId())
                .ageRange(userEntity.getAgeRange())
                .authorization(userEntity.isAuthorization())
                .profileImg(userEntity.getProfileImg())
                .name(userEntity.getName())
                .build();
        return userDto;
    }

    @Override
    public boolean isAuthAvail(Map<String, String> loginInfo) throws Exception {
        String loginId=loginInfo.get("id");
        UserEntity userEntity=userRepository.findByLoginId(loginId).orElseThrow(NoSuchElementException::new);
        if(userEntity.getPw().equals(loginInfo.get("pw"))){
            return true;
        }
        return false;
    }

    @Override
    public boolean withdrawalUser(int userId) {
        try{UserEntity userEntity= userRepository.findById((long)userId).orElseThrow(NoSuchElementException::new);
        userEntity.setUnavail();
        return true;}
        catch (Exception e){
            return false;
        }
    }

    @Override
    public int getUserIdByLoginId(String loginId) {
        UserEntity userEntity=userRepository.findByLoginId(loginId).orElseThrow(NoSuchElementException::new);
        return Math.toIntExact(userEntity.getUserId());
    }

    @Override
    public Map<String, String> getLoginIdAndNameByUserId(int userId) {
        UserEntity userEntity=userRepository.findById((long)userId).orElseThrow(NoSuchElementException::new);
        Map<String, String> userLoginIdAndName=new HashMap<>();
        userLoginIdAndName.put("loginId",userEntity.getLoginId());
        userLoginIdAndName.put("name",userEntity.getName());
        return userLoginIdAndName;
    }

    @Override
    public UserDto findUserByUserId(int userId) {
        UserEntity userEntity= userRepository.findById((long)userId).orElseThrow(NoSuchElementException::new);
        UserDto userDto =UserDto.builder()
                .email(userEntity.getEmail())
                .loginId(userEntity.getLoginId())
                .ageRange(userEntity.getAgeRange())
                .authorization(userEntity.isAuthorization())
                .profileImg(userEntity.getProfileImg())
                .name(userEntity.getName())
                .build();
        return userDto;
    }

    @Override
    public void removeUserRefreshToken(int userId) {
        UserEntity userEntity= userRepository.findById((long)userId).orElseThrow(NoSuchElementException::new);
        userEntity.setRefreshToken(null);
    }

}
