package com.bonvoyage.attraction.service;

import com.bonvoyage.attraction.dto.AttractionInfoDto;
import com.bonvoyage.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.attraction.repository.AttractionInfoRepository;
import lombok.RequiredArgsConstructor;
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
        System.out.println(entityList);
       List<AttractionInfoDto> result = new ArrayList<>();

       entityList.forEach(entity -> {
            AttractionInfoDto dto = new AttractionInfoDto();
            dto.setContentId(entity.getContentId());
            dto.setTitle(entity.getTitle());
            dto.setAddr1(entity.getAddr1());
            dto.setTel(entity.getTel());
            dto.setFirstImage(entity.getFirstImage());
            dto.setSidoCode(entity.getSidoCode());
            dto.setGugunCode(entity.getGugunCode());
            dto.setLatitude(entity.getLatitude());
            dto.setLongitude(entity.getLongitude());
           result.add(dto);
       });

       return result;

    }
}
