package com.bonvoyage.domain.user.like.service;

public interface UserLikeService {
    Object findUserLikeAttraction(int userId);

    void addUserLike(int userId, int contentId);

    void deleteUserLike(int userId, int contentId);
}
