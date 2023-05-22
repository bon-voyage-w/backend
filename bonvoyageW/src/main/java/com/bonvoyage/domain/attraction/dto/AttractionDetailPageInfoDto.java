package com.bonvoyage.domain.attraction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDetailPageInfoDto {
    @Schema(description = "관광지 기본 정보", required = true)
    private AttractionInfoDto attractionInfoDto;
    @Schema(description = "관광지 상세 설명", required = true)
    private String overview;
}
