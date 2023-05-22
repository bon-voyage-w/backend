package com.bonvoyage.domain.attraction.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attraction_description")
public class AttractionDescriptionEntity {

    @Id
    private Long contentId;
    private String homepage;
    private String overview;
    private String telname;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "contentId")
    private AttractionInfoEntity attractionInfoEntity;

    @Builder
    public AttractionDescriptionEntity(Long contentId, String homepage, String overview, String telname) {
        this.contentId = contentId;
        this.homepage = homepage;
        this.overview = overview;
        this.telname = telname;
    }
}
