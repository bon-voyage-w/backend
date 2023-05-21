package com.bonvoyage.domain.route.service;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
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
    public int addRoute(int userId,RouteDto routeDto) {
        RouteEntity routeEntity= RouteEntity.builder()
                .createdTime(routeDto.getCreatedTime())
                .routeTitle(routeDto.getTitle())
                .totalDays(routeDto.getRouteDailyList().size())
                .userId(userId)
                .build();
        RouteEntity newRouteEntity=routeRepository.save(routeEntity);
        int totalDays=newRouteEntity.getTotalDays();
        int newRouteId= Math.toIntExact(newRouteEntity.getRouteId());
        int i=1;
        for(RouteDto.RouteDetail routeDetail: routeDto.getRouteDailyList()){
            this.addRouteDetail(newRouteId,routeDetail);
        }
        return newRouteId;
    }
    private void addRouteDetail(int routeId, RouteDto.RouteDetail routeDetail){
        int visitSeq=1;
        for(int attractionId:routeDetail.getAttractionIdList()){
            RouteDetailEntity routeDetailEntity=RouteDetailEntity.builder()
                    .routeId(routeId)
                    .daySeq(routeDetail.getDaySeq())
                    .routeContent(attractionId)
                    .visitSeq(visitSeq)
                    .build();
            routeDetailRepository.save(routeDetailEntity);
            visitSeq++;
        }
    }
    @Override
    public void deleteRoute(int userId,int routeId) throws SecurityException {
        RouteEntity routeEntity=routeRepository.findById((long)routeId).orElseThrow(NoSuchElementException::new);
        if(routeEntity.getUserId()!=userId){
            throw new SecurityException();
        }
        routeRepository.deleteById((long)routeId);
    }

    @Override
    public RouteDto findRouteDetail(int routeId) {
        RouteEntity routeEntity=routeRepository.findById((long)routeId)
                .orElseThrow(NoSuchElementException::new);
        RouteDto routeDto=this.routeEntityToRouteDtoIncludingDetail(routeEntity);
        return routeDto;
    }

    @Override
    public int modifyRoute(int userId,int routeId,RouteDto routeDto) throws SecurityException {
        this.deleteRoute(userId,routeId);
        int newRouteId=this.addRoute(userId,routeDto);
        return newRouteId;
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
                .build();
        return routeDto;
    }
    private RouteDto routeEntityToRouteDtoIncludingDetail(RouteEntity routeEntity){

        RouteDto routeDto= RouteDto.builder()
                .routeId(Math.toIntExact(routeEntity.getRouteId()))
                .title(routeEntity.getRouteTitle())
                .createdTime(routeEntity.getCreatedTime())
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
                    .attractionInfoList(attractionInfoDtoListForDaySeq[daySeq-1]).build();
            routeDetailList.add(routeDetail);
        }
        return routeDetailList;
    }
}
