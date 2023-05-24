package com.bonvoyage.domain.attraction.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attraction_description")
public class AttractionDescriptionEntity {

    @Id
    private long contentId;
    private String homepage;
    private String overview;
    private String telname;

    @Builder
    public AttractionDescriptionEntity(long contentId, String homepage, String overview, String telname) {
        this.contentId = contentId;
        this.homepage = homepage;
        this.overview = overview;
        this.telname = telname;
    }
}
