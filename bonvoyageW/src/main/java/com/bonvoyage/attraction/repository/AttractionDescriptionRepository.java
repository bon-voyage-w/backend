package com.bonvoyage.attraction.repository;

import com.bonvoyage.attraction.entity.AttractionDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionDescriptionRepository extends JpaRepository<AttractionDescriptionEntity, Integer> {
}
