package com.bonvoyage.domain.attraction.repository;

import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionInfoRepository extends JpaRepository<AttractionInfoEntity, Integer> {
//    List<AttractionInfoEntity> findAll();
}
