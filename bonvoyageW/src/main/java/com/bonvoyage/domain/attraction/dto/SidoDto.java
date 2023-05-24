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
public class SidoDto {
    @Schema(description = "시도 코드", required = true)
    private Long sidoCode;
    @Schema(description = "시도 이름", required = true)
    private String sidoName;
}
