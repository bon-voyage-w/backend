package com.bonvoyage.attraction.repository;

import com.bonvoyage.attraction.entity.AttractionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionDetailRepository extends JpaRepository<AttractionDetailEntity, Integer> {
}
