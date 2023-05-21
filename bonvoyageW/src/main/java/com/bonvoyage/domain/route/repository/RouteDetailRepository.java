package com.bonvoyage.domain.route.repository;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.domain.route.entity.RouteDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteDetailRepository extends JpaRepository<RouteDetailEntity,Long> {
    List<RouteDetailEntity> findByRouteId(long routeId);
    List<RouteDetailEntity> findByRouteIdAndDaySeqOrderByVisitSeqAsc(long routeId,int daySeq);
}
