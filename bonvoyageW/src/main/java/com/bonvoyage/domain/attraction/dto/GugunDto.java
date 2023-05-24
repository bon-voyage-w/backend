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
public class GugunDto {
    @Schema(description = "구군 번호", required = true)
    private Long gugunCode;
    @Schema(description = "구군 이름", required = true)
    private String gugunName;
    @Schema(description = "시도 번호", required = true)
    private Long sidoCode;
}
