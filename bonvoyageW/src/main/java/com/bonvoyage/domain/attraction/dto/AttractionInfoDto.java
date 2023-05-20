package com.bonvoyage.domain.attraction.dto;

import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionInfoDto {

    private long contentId;
    private String title;
    private String addr1;
    private String tel;
    private String firstImage;
    private int sidoCode;
    private int gugunCode;
    private double latitude;
    private double longitude;


}
