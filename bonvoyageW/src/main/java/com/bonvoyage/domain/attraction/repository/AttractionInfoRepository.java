package com.bonvoyage.domain.attraction.repository;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionInfoRepository extends JpaRepository<AttractionInfoEntity, Long> {
    List<AttractionInfoEntity> findByTitleContaining(String title);

    List<AttractionInfoEntity> findByContentTypeId(Long contentTypeId);
}
