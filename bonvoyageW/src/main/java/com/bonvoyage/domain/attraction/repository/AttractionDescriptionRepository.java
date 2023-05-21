package com.bonvoyage.domain.attraction.repository;

import com.bonvoyage.domain.attraction.entity.AttractionDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionDescriptionRepository extends JpaRepository<AttractionDescriptionEntity, Integer> {
}
