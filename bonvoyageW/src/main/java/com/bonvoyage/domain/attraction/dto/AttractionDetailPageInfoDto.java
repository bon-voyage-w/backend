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

//    AttractionInfo
//    private long contentId;
//    private String title;
//    private String addr1;
//    private String tel;
//    private String firstImage;
//    private int sidoCode;
//    private int gugunCode;
//    private double latitude;
//    private double longitude;
    private AttractionInfoDto attractionInfoDto;
//    AttractionDescription
    private String overview;

//    AttractionDetail
//    private String cat1;
//    private String cat2;
//    private String cat3;
}
