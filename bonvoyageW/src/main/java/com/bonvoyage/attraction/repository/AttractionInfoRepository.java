package com.bonvoyage.attraction.repository;

import com.bonvoyage.attraction.entity.AttractionInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfoEntity, Integer> {
}
