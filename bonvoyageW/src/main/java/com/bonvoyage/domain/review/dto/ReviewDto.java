package com.bonvoyage.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private long reviewId;
    private String reviewContent;
    private Date writeDate;
    private String location;
    private int userId;
    private int contentId;

}
