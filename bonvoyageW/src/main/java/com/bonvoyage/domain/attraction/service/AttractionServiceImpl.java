package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.domain.attraction.repository.AttractionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;

    @Autowired
    public AttractionServiceImpl(AttractionInfoRepository attractionInfoRepository) {
        this.attractionInfoRepository = attractionInfoRepository;
    }

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
}
