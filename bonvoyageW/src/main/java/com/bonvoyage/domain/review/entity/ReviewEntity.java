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
    private Long reviewId;

    private String reviewContent;
    private Date writeDate;
    private String location;
    private int userId;
    private int contentId;

    @Builder

    public ReviewEntity(Long reviewId, String reviewContent, Date writeDate, String location, int userId, int contentId) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.writeDate = writeDate;
        this.location = location;
        this.userId = userId;
        this.contentId = contentId;
    }
}
