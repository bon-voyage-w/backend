package com.bonvoyage.domain.user.like.service;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.service.AttractionService;
import com.bonvoyage.domain.user.like.entity.UserLikeEntiry;
import com.bonvoyage.domain.user.like.repository.UserLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLikeServiceImpl implements UserLikeService{
    private final UserLikeRepository userLikeRepository;
    private final AttractionService attractionService;
    @Override
    public List<AttractionInfoDto> findUserLikeAttraction(int userId) {
        List<UserLikeEntiry> userLikeEntiryList= userLikeRepository.findByUserId(userId);
        List<AttractionInfoDto> attractionInfoDtoList = new ArrayList<>();
        for(UserLikeEntiry userLikeEntiry: userLikeEntiryList){
            attractionInfoDtoList.add(attractionService.findAttractionByContentId((long)userLikeEntiry.getContentId()));
        }
        return attractionInfoDtoList;
    }

    @Override
    public void addUserLike(int userId, int contentId) {
        userLikeRepository.save(UserLikeEntiry.builder().userId(userId).contentId(contentId).build());
    }

    @Override
    public void deleteUserLike(int userId, int contentId) {
        userLikeRepository.delete(UserLikeEntiry.builder().userId(userId).contentId(contentId).build());
    }
}
