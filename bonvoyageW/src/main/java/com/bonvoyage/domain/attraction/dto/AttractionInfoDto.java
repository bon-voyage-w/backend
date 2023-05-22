package com.bonvoyage.domain.attraction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionInfoDto {

    @Schema(description = "관광지 고유 번호", required = true)
//    @NotBlank
    private long contentId;
    @Schema(description = "관광지 분류 번호", required = true)
    private long contentTypeId;
    @Schema(description = "관광지 이름", required = true)
    private String title;
    @Schema(description = "관광지 주소", required = true)
    private String addr1;
    @Schema(description = "관광지 전화번호")
    private String tel;
    @Schema(description = "관광지 사진")
    private String firstImage;
    @Schema(description = "관광지 고유 번호", required = true)
    private int sidoCode;
    @Schema(description = "관광지 고유 번호", required = true)
    private int gugunCode;
    @Schema(description = "관광지 고유 번호", required = true)
    private double latitude;
    @Schema(description = "관광지 고유 번호", required = true)
    private double longitude;


}
