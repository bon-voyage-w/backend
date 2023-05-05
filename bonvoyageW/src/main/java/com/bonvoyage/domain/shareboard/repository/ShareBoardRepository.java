package com.bonvoyage.domain.shareboard.repository;

import com.bonvoyage.domain.shareboard.entity.ShareBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareBoardRepository extends JpaRepository<ShareBoardEntity,Long> {
}
