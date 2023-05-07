package com.bonvoyage.domain.review.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    private String reviewContent;
    private Date writeDate;
    private String location;
    private int userId;
    private int contentId2;

    @Builder

    public ReviewEntity(long reviewId, String reviewContent, Date writeDate, String location, int userId, int contentId2) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.writeDate = writeDate;
        this.location = location;
        this.userId = userId;
        this.contentId2 = contentId2;
    }
}
