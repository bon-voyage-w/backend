package com.bonvoyage.domain.route.service;

import com.bonvoyage.domain.route.dto.RouteDto;
import com.bonvoyage.domain.route.entity.RouteEntity;
import com.bonvoyage.domain.route.repository.RouteDetailRepository;
import com.bonvoyage.domain.route.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final RouteDetailRepository routeDetailRepository;
    @Override
    public List<RouteDto> findRouteList() {
        List<RouteEntity> routeEntityList=routeRepository.findAll();
        List<RouteDto> routeDtoList= new ArrayList<>();
        for (RouteEntity routeEntity: routeEntityList) {
            routeDtoList.add(this.routeEntityToRouteDto(routeEntity));
        }
        return routeDtoList;
    }

    @Override
    public int routeAdd(RouteDto routeDto) {
        return 0;
    }

    @Override
    public void deleteRoute(int routeId) {

    }

    @Override
    public RouteDto findRouteDetail(int routeId) {
        return null;
    }

    @Override
    public int modifyRoute(RouteDto routeDto) {
        return 0;
    }

    @Override
    public List<RouteDto> findRouteListWithDetail() {
        return null;
    }

    private RouteDto routeEntityToRouteDto(RouteEntity routeEntity){

        RouteDto routeDto= RouteDto.builder()
//                .title(routeEntity.getRouteTitle())
//                .createdTime(routeEntity.getCreatedTime())
//                .writerLoginId()
//                .writerName()
                .build();
        return routeDto;
    }
    private RouteDto routeEntityToRouteDtoIncludingDetail(RouteEntity routeEntity){
        RouteDto routeDto= RouteDto.builder()
//                .title()
//                .createdTime()
//                .writerLoginId()
//                .writerName()
                .build();
        return routeDto;
    }
}
