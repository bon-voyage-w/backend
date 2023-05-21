package com.bonvoyage.domain.route.service;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.domain.attraction.repository.AttractionInfoRepository;
import com.bonvoyage.domain.attraction.service.AttractionService;
import com.bonvoyage.domain.route.dto.RouteDto;
import com.bonvoyage.domain.route.entity.RouteDetailEntity;
import com.bonvoyage.domain.route.entity.RouteEntity;
import com.bonvoyage.domain.route.repository.RouteDetailRepository;
import com.bonvoyage.domain.route.repository.RouteRepository;
import com.bonvoyage.domain.user.repository.UserRepository;
import com.bonvoyage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final RouteDetailRepository routeDetailRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final AttractionService attractionService;
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
        RouteEntity routeEntity=routeRepository.findById((long)routeId)
                .orElseThrow(NoSuchElementException::new);
        RouteDto routeDto=this.routeEntityToRouteDtoIncludingDetail(routeEntity);
        return routeDto;
    }

    @Override
    public int modifyRoute(RouteDto routeDto) {

        return 0;
    }

    @Override
    public List<RouteDto> findRouteListWithDetail() {
        List<RouteEntity> routeEntityList=routeRepository.findAll();
        List<RouteDto> routeDtoList= new ArrayList<>();
        for (RouteEntity routeEntity: routeEntityList) {
            routeDtoList.add(this.routeEntityToRouteDtoIncludingDetail(routeEntity));
        }
        return routeDtoList;
    }

    private RouteDto routeEntityToRouteDto(RouteEntity routeEntity){
        RouteDto routeDto= RouteDto.builder()
                .routeId(Math.toIntExact(routeEntity.getRouteId()))
                .title(routeEntity.getRouteTitle())
                .createdTime(routeEntity.getCreatedTime())
                .writerLoginId(userRepository.getReferenceById((long)routeEntity.getUserId()).getLoginId())
                .writerName(userRepository.getReferenceById((long)routeEntity.getUserId()).getName())
                .build();
        return routeDto;
    }
    private RouteDto routeEntityToRouteDtoIncludingDetail(RouteEntity routeEntity){

        RouteDto routeDto= RouteDto.builder()
                .routeId(Math.toIntExact(routeEntity.getRouteId()))
                .title(routeEntity.getRouteTitle())
                .createdTime(routeEntity.getCreatedTime())
                .writerLoginId(userRepository.getReferenceById((long)routeEntity.getUserId()).getLoginId())
                .writerName(userRepository.getReferenceById((long)routeEntity.getUserId()).getName())
                .routeDailyList(this.findDailyRouteDetailDtoList(routeEntity))
                .build();
        return routeDto;
    }
    public List<RouteDto.RouteDetail> findDailyRouteDetailDtoList(RouteEntity routeEntity){
        int totalDays=routeEntity.getTotalDays();
        List<RouteDetailEntity>[] routeDetailEntityListForDaySeq= new ArrayList[totalDays];
        for(int daySeq=1;daySeq<= totalDays;daySeq++){
            routeDetailEntityListForDaySeq[daySeq-1]=routeDetailRepository.findByRouteIdAndDaySeqOrderByVisitSeqAsc(routeEntity.getRouteId(),daySeq);
        }
        List<AttractionInfoDto>[] attractionInfoDtoListForDaySeq= new List[totalDays];
        for(int daySeq=1;daySeq<= totalDays;daySeq++){
            int cntVisitAttraction=attractionInfoDtoListForDaySeq[daySeq-1].size();
            for(int i=0;i<cntVisitAttraction;i++){
                AttractionInfoDto attractionInfoDto=attractionService.findByContentId((long)routeDetailEntityListForDaySeq[daySeq-1].get(i).getRouteContent());
                attractionInfoDtoListForDaySeq[daySeq-1].add(attractionInfoDto);
            }
        }

        List<RouteDto.RouteDetail> routeDetailList= new ArrayList<>();
        for(int daySeq=1;daySeq<= totalDays;daySeq++){
            RouteDto.RouteDetail routeDetail= RouteDto.RouteDetail.builder()
                    .daySeq(daySeq)
                    .attractionList(attractionInfoDtoListForDaySeq[daySeq-1]).build();
            routeDetailList.add(routeDetail);
        }
        return routeDetailList;
    }
}
