package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.entity.AttractionDescriptionEntity;
import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.domain.attraction.repository.AttractionDescriptionRepository;
import com.bonvoyage.domain.attraction.repository.AttractionInfoRepository;
import com.bonvoyage.domain.attraction.specification.AttractionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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
    public List<AttractionInfoDto> findSearch(String keyword, int sidoCode, int gugunCode, Long contentTypeId) {
//        1. 조건 생성
//          1-1. 검색어 존재 여부
        Specification<AttractionInfoEntity> spec = (root, query, criteriaBuilder) -> null;
        if(keyword != null) {
            spec = spec.and(AttractionSpecification.containingTitle(keyword));
        }
//          1-2. 시도, 구군
        if(sidoCode > 0) {
            spec = spec.and(AttractionSpecification.equalSido(sidoCode));
            if(gugunCode > 0) {
                spec = spec.and(AttractionSpecification.equalGugun(gugunCode));
            }
        }
//        1-3. 분류
        if(contentTypeId != null) {

        }

//        2. 조건 적용
        List<AttractionInfoEntity> entityList = attractionInfoRepository.findAll(spec);
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
