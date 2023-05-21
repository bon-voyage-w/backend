package com.bonvoyage.domain.route.repository;

import com.bonvoyage.domain.route.entity.RouteDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteDetailRepository extends JpaRepository<RouteDetailEntity,Long> {
}
