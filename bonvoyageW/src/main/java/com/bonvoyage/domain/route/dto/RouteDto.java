package com.bonvoyage.domain.route.dto;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class RouteDto {
    int routeId;
    List<RouteDetail> routeDailyList;
    LocalDateTime createdTime;
    String title;
    @Data
    public static class RouteDetail{
        int daySeq;
        List<Integer> attractionIdList;
        List<AttractionInfoDto> attractionInfoList;
        @Builder
        public RouteDetail(int daySeq, List<AttractionInfoDto> attractionInfoList, List<Integer> attractionIdList) {
            this.daySeq = daySeq;
            this.attractionIdList=attractionIdList;
            this.attractionInfoList = attractionInfoList;
        }
    }
    @Builder
    public RouteDto(int routeId, List<RouteDetail> routeDailyList, LocalDateTime createdTime, String title) {
        this.routeId=routeId;
        this.routeDailyList = routeDailyList;
        this.createdTime = createdTime;
        this.title = title;
    }

}
