package com.bonvoyage.domain.shareboard.repository;

import com.bonvoyage.domain.shareboard.entity.ShareBoardCommentEntity;
import com.bonvoyage.domain.shareboard.entity.ShareBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareBoardCommentRepository extends JpaRepository<ShareBoardCommentEntity,Long> {
}
