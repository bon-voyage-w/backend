package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.entity.AttractionDescriptionEntity;
import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.domain.attraction.repository.AttractionDescriptionRepository;
import com.bonvoyage.domain.attraction.repository.AttractionInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;
    private final AttractionDescriptionRepository attractionDescriptionRepository;

    @Override
    public List<AttractionInfoDto> getAttractionList() {
       List<AttractionInfoEntity> entityList = attractionInfoRepository.findAll();
       List<AttractionInfoDto> result = new ArrayList<>();

        for (AttractionInfoEntity attractionInfoEntity : entityList) {

            AttractionInfoDto attractionInfoDto = entityToDto(attractionInfoEntity);
            result.add(attractionInfoDto);
        }

        return result;
    }

    @Override
    public List<AttractionInfoDto> findByTitle(String title) {
        List<AttractionInfoEntity> entityList = attractionInfoRepository.findByTitleContaining(title);
        List<AttractionInfoDto> result = new ArrayList<>();

        for(AttractionInfoEntity attractionInfoEntity : entityList) {
            AttractionInfoDto attractionInfoDto = entityToDto(attractionInfoEntity);
            result.add(attractionInfoDto);
        }

        return result;
    }

    @Override
    public AttractionDetailPageInfoDto findByContentId(Long contentId) {
        AttractionDescriptionEntity attractionDescriptionEntity = attractionDescriptionRepository.findByContentId(contentId);

        AttractionDetailPageInfoDto attractionDetailPageInfoDto = AttractionDetailPageInfoDto.builder()
                .attractionInfoDto(entityToDto(attractionDescriptionEntity.getAttractionInfoEntity()))
                .overview(attractionDescriptionEntity.getOverview())
                .build();

        return attractionDetailPageInfoDto;
    }

    @Override
    public List<AttractionInfoDto> findByContentTypeId(Long contentTypeId) {
        List<AttractionInfoEntity> entityList = attractionInfoRepository.findByContentTypeId(contentTypeId);
        List<AttractionInfoDto> result = new ArrayList<>();

        for(AttractionInfoEntity attractionInfoEntity : entityList) {
            AttractionInfoDto attractionInfoDto = entityToDto(attractionInfoEntity);
            result.add(attractionInfoDto);
        }

        return result;
    }

    public AttractionInfoDto entityToDto(AttractionInfoEntity attractionInfoEntity) {
        AttractionInfoDto attractionInfoDto = AttractionInfoDto.builder()
                .contentId(attractionInfoEntity.getContentId())
                .contentTypeId(attractionInfoEntity.getContentTypeId())
                .title(attractionInfoEntity.getTitle())
                .addr1(attractionInfoEntity.getAddr1())
                .tel(attractionInfoEntity.getTel())
                .firstImage(attractionInfoEntity.getFirstImage())
                .sidoCode(attractionInfoEntity.getSidoCode())
                .gugunCode(attractionInfoEntity.getGugunCode())
                .latitude(attractionInfoEntity.getLatitude())
                .longitude(attractionInfoEntity.getLongitude())
                .build();

        return attractionInfoDto;
    }

}
