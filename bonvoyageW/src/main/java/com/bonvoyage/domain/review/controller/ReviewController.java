package com.bonvoyage.domain.review.controller;

import com.bonvoyage.domain.review.dto.ReviewDto;
import com.bonvoyage.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<List<ReviewDto>> getReviews(@RequestParam(defaultValue = "0", required = false) Long contentId) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewList(contentId));
    }

    @PostMapping("")
    public ResponseEntity<Long> egisterReview(@RequestBody ReviewDto reviewDto){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.addReview(reviewDto));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Long> modifyNotice(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewDto reviewDto){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.modifyReview(reviewId, reviewDto));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Long> deleteNotice(@PathVariable("reviewId") Long reviewId) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.removeReview(reviewId));

    }

}
