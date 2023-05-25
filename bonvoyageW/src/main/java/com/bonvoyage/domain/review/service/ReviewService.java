package com.bonvoyage.domain.review.service;

import com.bonvoyage.domain.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    Long addReview(int userId,ReviewDto reviewDto);
    Long modifyReview(int userId,Long reviewId, ReviewDto reviewDto);
    Long removeReview(int userId,Long reviewId);

    List<ReviewDto> getReviewList(Long contentId);
    List<ReviewDto> findReviewListByLoginId(String loginId);
    List<ReviewDto> findReviewListByUserId(int userId);

}
