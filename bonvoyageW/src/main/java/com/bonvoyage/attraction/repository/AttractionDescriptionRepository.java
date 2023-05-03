package com.bonvoyage.attraction.repository;

import com.bonvoyage.attraction.entity.AttractionDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionDescriptionRepository extends JpaRepository<AttractionDescriptionEntity, Integer> {
}
