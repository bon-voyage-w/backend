package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.dto.GugunDto;
import com.bonvoyage.domain.attraction.dto.SidoDto;
import com.bonvoyage.domain.attraction.entity.AttractionDescriptionEntity;
import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.domain.attraction.entity.GugunEntity;
import com.bonvoyage.domain.attraction.entity.SidoEntity;
import com.bonvoyage.domain.attraction.repository.AttractionDescriptionRepository;
import com.bonvoyage.domain.attraction.repository.AttractionInfoRepository;
import com.bonvoyage.domain.attraction.repository.GugunRepository;
import com.bonvoyage.domain.attraction.repository.SidoRepository;
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
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;
    private final AttractionDescriptionRepository attractionDescriptionRepository;
    private final GugunRepository gugunRepository;
    private final SidoRepository sidoRepository;

    @Override
    public Page<AttractionInfoDto> getAttractionList(int pageNumber, int pageSize) {
//        [test] paging 처리
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "contentId");
        Page<AttractionInfoEntity> entityList = attractionInfoRepository.findAll(page);
        return toDtoList(entityList);
    }

    @Override
    public Page<AttractionInfoDto> findSearch(String keyword, int sidoCode, int gugunCode, int contentTypeId, int pageNumber, int pageSize) {
        Long contentCategoryId = new Long(contentTypeId);
//        1. 조건 생성
//          1-1. 검색어 존재 여부
        Specification<AttractionInfoEntity> spec = (root, query, criteriaBuilder) -> null;
        if (keyword != null) {
            String trimKeyword = keyword.trim();
            spec = spec.and(AttractionSpecification.containingTitle(trimKeyword));
        }
//          1-2. 시도, 구군
        if (sidoCode > 0) {
            spec = spec.and(AttractionSpecification.equalSido(sidoCode));
        }
        if (gugunCode > 0) {
            spec = spec.and(AttractionSpecification.equalGugun(gugunCode));
        }
//        1-3. 분류
        if(contentCategoryId != 0) {
            spec = spec.and(AttractionSpecification.equalContentTypeId(contentCategoryId));
        }

//        2. 조건 적용
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "contentId");
        Page<AttractionInfoEntity> entityList = attractionInfoRepository.findAll(spec, page);
        return toDtoList(entityList);
    }

    @Override
    public List<SidoDto> getSidoList() {
        List<SidoEntity> sidoEntities = sidoRepository.findAll();
        List<SidoDto> result = new ArrayList<>();

        for (SidoEntity sidoEntity : sidoEntities) {
            SidoDto sidoDto = SidoDto.builder()
                    .sidoCode(sidoEntity.getSidoCode())
                    .sidoName(sidoEntity.getSidoName())
                    .build();
            result.add(sidoDto);
        }

        return result;
    }

    @Override
    public List<GugunDto> getGugunList(Long sidoCode) {
        List<GugunEntity> gugunEntities = gugunRepository.findBySidoEntity_SidoCode(sidoCode);
        List<GugunDto> result = new ArrayList<>();

        for(GugunEntity gugunEntity : gugunEntities) {
            GugunDto gugunDto = GugunDto.builder()
                    .gugunCode(gugunEntity.getGugunCode())
                    .gugunName(gugunEntity.getGugunName())
                    .sidoCode(gugunEntity.getSidoEntity().getSidoCode())
                    .build();
            System.out.println("@@@@@@@@@@@@@@@@@@@ " + gugunDto.getGugunName());
            result.add(gugunDto);
        }
        return result;
    }

    @Override
    public AttractionDetailPageInfoDto findAttractionDetailByContentId(Long contentId) {
        AttractionDescriptionEntity attractionDescriptionEntity = attractionDescriptionRepository.findByContentId(contentId);

        AttractionDetailPageInfoDto attractionDetailPageInfoDto = AttractionDetailPageInfoDto.builder()
                .attractionInfoDto(entityToDto(attractionDescriptionEntity.getAttractionInfoEntity()))
                .overview(attractionDescriptionEntity.getOverview())
                .build();

        return attractionDetailPageInfoDto;
    }
    public AttractionInfoDto findAttractionByContentId(Long contentId) {
        AttractionInfoEntity attractionInfoEntity = attractionInfoRepository.findById(contentId).orElseThrow(NoSuchElementException::new);
        AttractionInfoDto attractionInfoDto=entityToDto(attractionInfoEntity);
        return attractionInfoDto;
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
}
