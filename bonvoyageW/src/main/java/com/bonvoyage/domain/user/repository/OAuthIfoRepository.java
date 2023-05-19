package com.bonvoyage.domain.user.repository;

import com.bonvoyage.domain.user.entity.OAuthInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface OAuthIfoRepository extends JpaRepository<OAuthInfoEntity,Long> {
    Optional<OAuthInfoEntity> findByOauthId(long oauthId);
    boolean existsByOauthId(long oauthId);
}
