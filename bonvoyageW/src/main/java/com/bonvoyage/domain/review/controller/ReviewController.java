package com.bonvoyage.domain.review.controller;

import com.bonvoyage.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/")
    public String getReviews() {
        return "";
    }

    @PostMapping("/")
    public String registerReview(){
        return "";
    }

    @PutMapping("/{reviewId}")
    public String modifyNotice(){
        return "";
    }

    @DeleteMapping("/{reviewId}")
    public String deleteNotice() {
        return "";
    }




}
