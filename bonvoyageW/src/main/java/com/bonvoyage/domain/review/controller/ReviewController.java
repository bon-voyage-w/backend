package com.bonvoyage.domain.review.controller;

import com.bonvoyage.domain.review.dto.ReviewDto;
import com.bonvoyage.domain.review.service.ReviewService;
import com.bonvoyage.domain.user.service.JWTService;
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
    private final JWTService jwtService;

    @GetMapping("")
    public ResponseEntity<?> getReviews(@RequestHeader("Authorization") String accessToken,
                                                      @RequestParam(defaultValue = "0", required = false) Long contentId) {
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewList( contentId));
    }

    @PostMapping("")
    public ResponseEntity<?> registerReview(@RequestHeader("Authorization") String accessToken,
                                               @RequestBody ReviewDto reviewDto){
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.addReview(userId,reviewDto));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> modifyNotice(@RequestHeader("Authorization") String accessToken,
                                             @PathVariable("reviewId") Long reviewId, @RequestBody ReviewDto reviewDto){
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.modifyReview(userId,reviewId,reviewDto));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteNotice(@RequestHeader("Authorization") String accessToken,
                                             @PathVariable("reviewId") Long reviewId) {
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.removeReview(userId,reviewId));

    }

}
