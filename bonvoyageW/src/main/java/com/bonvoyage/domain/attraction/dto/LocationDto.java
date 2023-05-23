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
public class LocationDto {
    @Schema(description = "관광지 고유 번호", required = true)
    private int sidoCode;
    @Schema(description = "관광지 고유 번호", required = true)
    private int gugunCode;
    @Schema(description = "시도 이름", required = true)
    private int sidoName;
    @Schema(description = "구군 이름", required = true)
    private int gugunName;
}
