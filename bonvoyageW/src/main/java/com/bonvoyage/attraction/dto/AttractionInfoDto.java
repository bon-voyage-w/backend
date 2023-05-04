package com.bonvoyage.attraction.dto;

import com.bonvoyage.attraction.entity.AttractionInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionInfoDto {

    private int contentId;
    private String title;
    private String addr1;
    private String tel;
    private String firstImage;
    private int sidoCode;
    private int gugunCode;
    private double latitude;
    private double longitude;

//    public static AttractionInfoDto toDto(AttractionInfoEntity attractionInfoEntity) {
//        return AttractionInfoDto.builder()
//                .contentId(attractionInfoEntity.getContentId())
//                .title(attractionInfoEntity.getTitle())
//                .addr1(attractionInfoEntity.getAddr1())
//                .tel(attractionInfoEntity.getTel())
//                .firstImage(attractionInfoEntity.getFirstImage())
//                .sidoCode(attractionInfoEntity.getSidoCode())
//                .gugunCode(attractionInfoEntity.getGugunCode())
//                .latitude(attractionInfoEntity.getLatitude())
//                .longitude(attractionInfoEntity.getLongitude())
//                .build();
//    }

}
