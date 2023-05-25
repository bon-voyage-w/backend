package com.bonvoyage.domain.shareboard.repository;

import com.bonvoyage.domain.shareboard.entity.ShareBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareBoardRepository extends JpaRepository<ShareBoardEntity,Long> {
}
