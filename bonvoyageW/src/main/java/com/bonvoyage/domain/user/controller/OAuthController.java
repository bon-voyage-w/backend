package com.bonvoyage.domain.user.controller;

import com.bonvoyage.domain.user.client.KakaoApiClient;
import com.bonvoyage.domain.user.client.KakaoTokenClient;
import com.bonvoyage.domain.user.dto.KakaoTokenDto;
import com.bonvoyage.domain.user.dto.KakaoUserInfoDto;
import com.bonvoyage.domain.user.entity.UserEntity;
import com.bonvoyage.domain.user.service.JWTService;
import com.bonvoyage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {
    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoApiClient kakaoApiClient;
    private final UserService userService;
    private final JWTService jwtService;

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }
    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;
    @GetMapping("/kakao/callback")
    public @ResponseBody ResponseEntity<?> kakaoLoginCallback(String code) {
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        KakaoTokenDto.Request kakaoTokenRequestDto = KakaoTokenDto.Request.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .grant_type("authorization_code")
                .code(code)
                .redirect_uri("http://localhost:8085/oauth/kakao/callback")
                .build();
        KakaoTokenDto.Response kakaoToken = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequestDto);
        KakaoUserInfoDto userInfoDto=kakaoApiClient.requestKakaoUserInfo("Bearer "+kakaoToken.getAccess_token());

        int userId;
        if(!userService.isRegisterUser(userInfoDto.getId())){
            userId=userService.registerUser(userInfoDto);
            userService.registerOauthInfo(userId,userInfoDto.getId(),kakaoToken);
        }
        else{
            try{
            userId=userService.getUserIdByOauth(userInfoDto);}
            catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.noContent().build();
            }
        }
        String accessToken=jwtService.createAccessToken("userId",userId);
        String refreshToken=jwtService.createRefreshToken("userId",userId);
        Map<String, String> token= new HashMap<>();
        token.put("access_token",accessToken);
        token.put("refresh_token",refreshToken);

        return ResponseEntity.ok().body(token);
    }

}
