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
    String type;
    long oauthId;

    @Builder
    public OAuthInfoEntity(Long oauthInfoId, int userId, String accessToken, String refreshToken, String type, long oauthId) {
        this.oauthInfoId = oauthInfoId;
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.type = type;
        this.oauthId = oauthId;
    }
}
