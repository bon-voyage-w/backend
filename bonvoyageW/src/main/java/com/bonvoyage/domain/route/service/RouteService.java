package com.bonvoyage.domain.route.service;

import com.bonvoyage.domain.route.dto.RouteDto;

import java.util.List;

public interface RouteService {
    List<RouteDto> findRouteList();

    int addRoute(int userId, RouteDto routeDto);

    void deleteRoute(int userId,int routeId) throws IllegalAccessException;

    RouteDto findRouteDetail(int routeId);

    int modifyRoute(int userId,int routeId,RouteDto routeDto) throws IllegalAccessException;

    List<RouteDto> findRouteListWithDetail();
}
