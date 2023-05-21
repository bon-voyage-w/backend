package com.bonvoyage.domain.route.dto;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class RouteDto {
    int routeId;
    String writerName;
    String writerLoginId;
    List<RouteDetail> routeDailyList;
    LocalDateTime createdTime;
    String title;
    @Data
    public static class RouteDetail{
        int daySeq;
        List<AttractionInfoDto> attractionList;
        @Builder
        public RouteDetail(int daySeq, List<AttractionInfoDto> attractionList) {
            this.daySeq = daySeq;
            this.attractionList = attractionList;
        }
    }
    @Builder
    public RouteDto(int routeId,String writerName, String writerLoginId, List<RouteDetail> routeDailyList, LocalDateTime createdTime, String title) {
        this.routeId=routeId;
        this.writerName = writerName;
        this.writerLoginId = writerLoginId;
        this.routeDailyList = routeDailyList;
        this.createdTime = createdTime;
        this.title = title;
    }

}
