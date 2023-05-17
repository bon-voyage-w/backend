package com.bonvoyage.domain.review.service;

import com.bonvoyage.domain.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    void addReview(ReviewDto reviewDto);
    void modifyReview(Long reviewId, ReviewDto reviewDto);
    void removeReview(Long reviewId);

    List<ReviewDto> getReviewList();
}
