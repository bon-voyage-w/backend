package com.bonvoyage.domain.review.service;

import com.bonvoyage.domain.review.dto.ReviewDto;
import com.bonvoyage.domain.review.entity.ReviewEntity;
import com.bonvoyage.domain.review.repository.ReviewRepository;
import com.bonvoyage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;

    @Override
    public Long addReview(ReviewDto reviewDto) {
        reviewRepository.save(dtoToEntity(reviewDto));
        return reviewDto.getReviewId();
    }

    @Override
    public Long modifyReview(Long reviewId, ReviewDto reviewDto) {
        ReviewEntity reviewEntity = reviewRepository.findById(reviewId)
                .orElseThrow(NullPointerException::new);
        reviewEntity.update(reviewDto.getReviewContent());
        return reviewId;
    }

    @Override
    public Long removeReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
        return reviewId;
    }

    @Override
    public List<ReviewDto> getReviewList(Long contentId) {
        List<ReviewDto> result = new ArrayList<>();
        List<ReviewEntity> entityList = new ArrayList<>();

        if(contentId == 0) {
            entityList = reviewRepository.findAll();
        }
        else {
            entityList = reviewRepository.findByContentId(contentId);
        }

        for(ReviewEntity reviewEntity : entityList) {
            ReviewDto reviewDto = entityToDto(reviewEntity);
            result.add(reviewDto);
        }
        return result;
    }

    @Override
    public List<ReviewDto> findReviewListByLoginId(String loginId) {
        List<ReviewDto> result = new ArrayList<>();
        List<ReviewEntity> entityList=reviewRepository.findByUserId(userService.getUserIdByLoginId(loginId));
        for(ReviewEntity reviewEntity : entityList) {
            ReviewDto reviewDto = entityToDto(reviewEntity);
            result.add(reviewDto);
        }
        return result;
    }

    @Override
    public List<ReviewDto> findReviewListByUserId(int userId) {
        List<ReviewDto> result = new ArrayList<>();
        List<ReviewEntity> entityList=reviewRepository.findByUserId(userId);
        for(ReviewEntity reviewEntity : entityList) {
            ReviewDto reviewDto = entityToDto(reviewEntity);
            result.add(reviewDto);
        }
        return result;
    }

    public ReviewDto entityToDto(ReviewEntity reviewEntity) {
        Map<String,String> userInfo =userService.getLoginIdAndNameByUserId(reviewEntity.getUserId());
        ReviewDto reviewDto = ReviewDto.builder()
                .reviewId(reviewEntity.getReviewId())
                .reviewContent(reviewEntity.getReviewContent())
                .contentId(reviewEntity.getContentId())
                .writeDate(reviewEntity.getWriteDate())
                .location(reviewEntity.getLocation())
                .writerLoginId(userInfo.get("loginId"))
                .writerName(userInfo.get("name"))
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
                .userId(userService.getUserIdByLoginId(reviewDto.getWriterLoginId()))
                .contentId(reviewDto.getContentId())
                .build();
        return reviewEntity;
    }

}
