package com.bonvoyage.attraction.repository;

import com.bonvoyage.attraction.entity.AttractionInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.List;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfoEntity, Integer> {
    List<AttractionInfoEntity> findAll();
=======
public interface AttractionInfoRepository extends JpaRepository<AttractionInfoEntity, Integer> {
>>>>>>> origin/feat/attraction
}
