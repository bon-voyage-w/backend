package com.bonvoyage.attraction.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attraction_detail")
public class AttractionDetailEntity {

    private int contentId;
    private String cat1;
    private String cat2;
    private String cat3;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    private String booktour;

    public AttractionDetailEntity(int contentId, String cat1, String cat2, String cat3, LocalDateTime created_time, LocalDateTime modified_time, String booktour) {
        this.contentId = contentId;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.created_time = created_time;
        this.modified_time = modified_time;
        this.booktour = booktour;
    }

}
