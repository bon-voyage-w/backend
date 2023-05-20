package com.bonvoyage.domain.route.dto;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;

import java.util.List;

public class RouteDto {
    List<RouteDaily> routeDailyList;
    public class RouteDaily{
        List<AttractionInfoDto> routes;
    }
}
