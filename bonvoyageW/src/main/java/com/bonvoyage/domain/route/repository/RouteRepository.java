package com.bonvoyage.domain.route.repository;

import com.bonvoyage.domain.route.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<RouteEntity,Long> {
}
