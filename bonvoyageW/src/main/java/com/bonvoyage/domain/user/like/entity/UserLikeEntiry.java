package com.bonvoyage.domain.user.like.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserLikePk.class)
@Table(name="user_likes")
public class UserLikeEntiry {
    @Builder
    public UserLikeEntiry(int userId, int contentId) {
        this.userId = userId;
        this.contentId = contentId;
    }

    @Id
    private int userId;

    @Id
    private int contentId;

}
