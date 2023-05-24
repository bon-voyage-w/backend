package com.bonvoyage.domain.user.service;

public interface JWTService {

    <T> String createAccessToken(String key, T data);
    <T> String createRefreshToken(String key, T data);
    <T> String create(String key, T data, String subject, long expir);
    boolean isUnavailToken(String jwt);
    int getUserId(String authorization);
}
