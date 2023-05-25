package com.bonvoyage.domain.user.like.repository;

import com.bonvoyage.domain.user.like.entity.UserLikeEntiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLikeRepository extends JpaRepository<UserLikeEntiry,Integer> {
    List<UserLikeEntiry> findByUserId(int userId);
    List<Integer> getContentIdByUserId(int userId);
}
