package com.bonvoyage.domain.review.service;

import com.bonvoyage.domain.review.dto.ReviewDto;
import com.bonvoyage.domain.review.entity.ReviewEntity;
import com.bonvoyage.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public void addReview(ReviewDto reviewDto) {
        reviewRepository.save(dtoToEntity(reviewDto));
    }

    @Override
    public void modifyReview(Long reviewId, ReviewDto reviewDto) {
        ReviewEntity reviewEntity = reviewRepository.findById(reviewId)
                .orElseThrow(NullPointerException::new);
        reviewEntity.update(reviewDto.getReviewContent());
    }

    @Override
    public void removeReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<ReviewDto> getReviewList() {
        List<ReviewDto> result = new ArrayList<>();
        List<ReviewEntity> entityList = reviewRepository.findAll();
        for(ReviewEntity reviewEntity : entityList) {
            ReviewDto reviewDto = entityToDto(reviewEntity);
            result.add(reviewDto);
        }
        return result;
    }

    @Override
    public List<ReviewDto> findByContentId(Long contentId) {
        List<ReviewDto> result = new ArrayList<>();
        List<ReviewEntity> entityList = reviewRepository.findByContentId(contentId);
        for(ReviewEntity reviewEntity : entityList) {
            ReviewDto reviewDto = entityToDto(reviewEntity);
            result.add(reviewDto);
        }
        return result;
    }

    @Override
    public List<ReviewDto> findByUserId(Long userId) {
        List<ReviewDto> result = new ArrayList<>();
        List<ReviewEntity> entityList = reviewRepository.findByUserId(userId);
        for(ReviewEntity reviewEntity : entityList) {
            ReviewDto reviewDto = entityToDto(reviewEntity);
            result.add(reviewDto);
        }
        return result;
    }

    public ReviewDto entityToDto(ReviewEntity reviewEntity) {
        ReviewDto reviewDto = ReviewDto.builder()
                .reviewId(reviewEntity.getReviewId())
                .contentId(reviewEntity.getContentId())
                .writeDate(reviewEntity.getWriteDate())
                .location(reviewEntity.getLocation())
                .userId(reviewEntity.getUserId())
                .contentId(reviewEntity.getContentId())
                .build();
        return reviewDto;
    }

    public ReviewEntity dtoToEntity(ReviewDto reviewDto) {
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .reviewId(reviewDto.getReviewId())
                .reviewContent(reviewDto.getReviewContent())
                .writeDate(reviewDto.getWriteDate())
                .location(reviewDto.getLocation())
                .userId(reviewDto.getUserId())
                .contentId(reviewDto.getContentId())
                .build();
        return reviewEntity;
    }

}
