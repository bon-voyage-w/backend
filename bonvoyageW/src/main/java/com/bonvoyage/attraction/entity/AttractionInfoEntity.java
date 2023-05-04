package com.bonvoyage.attraction.entity;

import com.bonvoyage.attraction.dto.AttractionInfoDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
//@Entity(name = "attraciton_info")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attraction_info")
public class AttractionInfoEntity {

    @Id
    @Column(name="content_id")
    private int contentId;

    @Column(name="content_type_id")
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    @Column(name="first_image")
    private String firstImage;
    @Column(name="first_image2")
    private String firstImage2;
    private int readcount;
    @Column(name="sido_code")
    private int sidoCode;
    @Column(name="gugun_code")
    private int gugunCode;
    private double latitude;
    private double longitude;
    private String mlevel;

    @Builder
    public AttractionInfoEntity(int contentId, int contentTypeId, String title, String addr1, String addr2, String zipcode, String tel, String firstImage, String firstImage2, int readcount, int sidoCode, int gugunCode, double latitude, double longitude, String mlevel) {
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.zipcode = zipcode;
        this.tel = tel;
        this.firstImage = firstImage;
        this.firstImage2 = firstImage2;
        this.readcount = readcount;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mlevel = mlevel;
    }

}

