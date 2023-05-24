package com.bonvoyage.domain.user.like.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLikePk implements Serializable {
    private int userId;
    private int contentId;
}
