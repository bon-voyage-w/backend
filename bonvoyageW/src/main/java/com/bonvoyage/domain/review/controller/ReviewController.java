package com.bonvoyage.domain.review.controller;

import com.bonvoyage.domain.review.dto.ReviewDto;
import com.bonvoyage.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("")
    public List<ReviewDto> getReviews() {
        return reviewService.getReviewList();
    }

    @PostMapping("")
    public String registerReview(@RequestBody ReviewDto reviewDto){
        reviewService.addReview(reviewDto);
        return "success";
    }

    @PutMapping("/{reviewId}")
    public String modifyNotice(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewDto reviewDto){
        reviewService.modifyReview(reviewId, reviewDto);
        return "success";
    }

    @DeleteMapping("/{reviewId}")
    public Long deleteNotice(@PathVariable("reviewId") Long reviewId) {
        reviewService.removeReview(reviewId);
        return reviewId;
    }




}
