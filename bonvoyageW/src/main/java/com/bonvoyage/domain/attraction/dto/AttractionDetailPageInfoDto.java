package com.bonvoyage.domain.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDetailPageInfoDto {

    private AttractionInfoDto attractionInfoDto;
    private String overview;
}
