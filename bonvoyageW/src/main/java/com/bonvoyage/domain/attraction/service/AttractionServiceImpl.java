package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.dto.LocationDto;
import com.bonvoyage.domain.attraction.dto.SidoDto;
import com.bonvoyage.domain.attraction.entity.AttractionDescriptionEntity;
import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.domain.attraction.entity.GugunEntity;
import com.bonvoyage.domain.attraction.entity.SidoEntity;
import com.bonvoyage.domain.attraction.repository.AttractionDescriptionRepository;
import com.bonvoyage.domain.attraction.repository.AttractionInfoRepository;
import com.bonvoyage.domain.attraction.repository.GugunRepository;
import com.bonvoyage.domain.attraction.specification.AttractionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;
    private final AttractionDescriptionRepository attractionDescriptionRepository;
    private final GugunRepository gugunRepository;

    @Override
    public Page<AttractionInfoDto> getAttractionList() {
//        [test] paging 처리
        Pageable page = PageRequest.of(0, 6, Sort.Direction.DESC, "contentId");
        Page<AttractionInfoEntity> entityList = attractionInfoRepository.findAll(page);
        return toDtoList(entityList);
    }

    @Override
    public Page<AttractionInfoDto> findSearch(String keyword, int sidoCode, int gugunCode, int contentTypeId) {
        Long contentCategoryId = new Long(contentTypeId);

//        1. 조건 생성
//          1-1. 검색어 존재 여부
        Specification<AttractionInfoEntity> spec = (root, query, criteriaBuilder) -> null;
        if (keyword != null) {
            spec = spec.and(AttractionSpecification.containingTitle(keyword));
        }
//          1-2. 시도, 구군
        if (sidoCode > 0) {
            spec = spec.and(AttractionSpecification.equalSido(sidoCode));
            if (gugunCode > 0) {
                spec = spec.and(AttractionSpecification.equalGugun(gugunCode));
            }
        }
//        1-3. 분류
//        if(contentCategoryId != null) {
//
//        }

//        2. 조건 적용
        Pageable page = PageRequest.of(0, 6, Sort.Direction.DESC, "contentId");
        Page<AttractionInfoEntity> entityList = attractionInfoRepository.findAll(spec, page);
        return toDtoList(entityList);
    }

    @Override
    public List<LocationDto> getLocationList() {
        List<GugunEntity> gugunEntities = gugunRepository.findAll();
        List<LocationDto> result = new ArrayList<>();

        for(GugunEntity entity : gugunEntities) {
            LocationDto locationDto = locationEntityToDto(entity);
            result.add(locationDto);
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

    public Page<AttractionInfoDto> toDtoList(Page<AttractionInfoEntity> attractionInfoEntityList) {
        Page<AttractionInfoDto> attractionInfoDtoList = attractionInfoEntityList.map(attractionInfoEntity -> AttractionInfoDto.builder()
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
                .build());
        return attractionInfoDtoList;
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

    public LocationDto locationEntityToDto(GugunEntity gugunEntity) {
        SidoEntity tmpSidoEntity = gugunEntity.getSidoEntity();

      LocationDto locationDto = LocationDto.builder()
              .sidoDto(
                      SidoDto.builder()
                              .sidoCode(gugunEntity.getSidoEntity().getSidoCode())
                              .sidoName(gugunEntity.getSidoEntity().getSidoName())
                              .build()
              )
              .gugunCode(gugunEntity.getGugunCode())
              .gugunName(gugunEntity.getGugunName())
              .build();

        return locationDto;
    }

}
