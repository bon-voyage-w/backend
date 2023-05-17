package com.bonvoyage.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long reviewId;
    private String reviewContent;
    private Date writeDate;
    private String location;
    private Long userId;
    private Long contentId;

}
