package com.bonvoyage.domain.route.dto;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class RouteDto {

    String writerName;
    String writerLoginId;
    List<RouteDetail> routeDailyList;
    LocalDateTime createdTime;
    String title;
    public class RouteDetail{
        int daySeq;
        List<AttractionInfoDto> attractionList;
    }
    @Builder
    public RouteDto(String writerName, String writerLoginId, List<RouteDetail> routeDailyList, LocalDateTime createdTime, String title) {
        this.writerName = writerName;
        this.writerLoginId = writerLoginId;
        this.routeDailyList = routeDailyList;
        this.createdTime = createdTime;
        this.title = title;
    }

}
