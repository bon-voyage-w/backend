package com.bonvoyage.domain.attraction.repository;

import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionInfoRepository extends JpaRepository<AttractionInfoEntity, Long>, JpaSpecificationExecutor<AttractionInfoEntity> {
    Page<AttractionInfoEntity> findAll(Pageable pageable);
    Page<AttractionInfoEntity> findAll(Specification spec, Pageable pageable);
}
