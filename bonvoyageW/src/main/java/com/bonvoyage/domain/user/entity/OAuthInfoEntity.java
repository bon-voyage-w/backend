package com.bonvoyage.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="oauth_info")

public class OAuthInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long oauthInfoId;

    int userId;
    String accessToken;
    String refreshToken;
    @Enumerated(EnumType.STRING)
    OAuth2 type;
    public enum OAuth2 {kakao, google, naver}

    long oauthId;

    @Builder
    public OAuthInfoEntity(Long oauthInfoId, int userId, String accessToken, String refreshToken, OAuth2 type, long oauthId) {
        this.oauthInfoId = oauthInfoId;
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.type = type;
        this.oauthId = oauthId;
    }
}
