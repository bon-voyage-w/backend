package com.bonvoyage.attraction.repository;

import com.bonvoyage.attraction.entity.AttractionInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionInfoRepository extends JpaRepository<AttractionInfoEntity, Integer> {
//    List<AttractionInfoEntity> findAll();
}
