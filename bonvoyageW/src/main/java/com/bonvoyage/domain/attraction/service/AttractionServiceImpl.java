package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.domain.attraction.repository.AttractionInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;

    @Override
    public List<AttractionInfoDto> getAttractionList() {
       List<AttractionInfoEntity> entityList = attractionInfoRepository.findAll();
       List<AttractionInfoDto> result = new ArrayList<>();

        for (AttractionInfoEntity attractionInfoEntity : entityList) {
            AttractionInfoDto attractionInfoDto = AttractionInfoDto.builder()
                    .contentId(attractionInfoEntity.getContentId())
                    .title(attractionInfoEntity.getTitle())
                    .addr1(attractionInfoEntity.getAddr1())
                    .tel(attractionInfoEntity.getTel())
                    .firstImage(attractionInfoEntity.getFirstImage())
                    .sidoCode(attractionInfoEntity.getSidoCode())
                    .gugunCode(attractionInfoEntity.getGugunCode())
                    .latitude(attractionInfoEntity.getLatitude())
                    .longitude(attractionInfoEntity.getLongitude())
                    .build();

            result.add(attractionInfoDto);
        }

        return result;
    }

    @Override
    public AttractionInfoDto view(long contentId) {

        AttractionInfoEntity attractionInfoEntity = attractionInfoRepository.findById(contentId)
                .orElseThrow(NullPointerException::new);

        System.out.println("@@@@@@@@@@@ s " + contentId);

        AttractionInfoDto attractionInfoDto = AttractionInfoDto.builder()
                .contentId(attractionInfoEntity.getContentId())
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
