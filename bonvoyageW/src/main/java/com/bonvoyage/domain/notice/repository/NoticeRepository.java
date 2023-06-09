package com.bonvoyage.domain.notice.repository;

import com.bonvoyage.domain.notice.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    Page<NoticeEntity> findAll(Pageable pageable);
}
