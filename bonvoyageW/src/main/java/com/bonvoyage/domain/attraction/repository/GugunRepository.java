package com.bonvoyage.domain.attraction.repository;

import com.bonvoyage.domain.attraction.dto.GugunDto;
import com.bonvoyage.domain.attraction.entity.GugunEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GugunRepository extends JpaRepository<GugunEntity, Long> {
    List<GugunEntity> findBySidoEntity_SidoCode(Long sidoCode);
}
