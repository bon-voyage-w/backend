package com.bonvoyage.domain.review.service;

import com.bonvoyage.domain.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    Long addReview(ReviewDto reviewDto);
    Long modifyReview(Long reviewId, ReviewDto reviewDto);
    Long removeReview(Long reviewId);

    List<ReviewDto> getReviewList(Long contentId);
}
