package com.bonvoyage.domain.route.service;

import com.bonvoyage.domain.route.dto.RouteDto;

import java.util.List;

public interface RouteService {
    List<RouteDto> findRouteList();

    int routeAdd(RouteDto routeDto);

    void deleteRoute(int routeId);

    RouteDto findRouteDetail(int routeId);

    int modifyRoute(RouteDto routeDto);

    List<RouteDto> findRouteListWithDetail();
}
