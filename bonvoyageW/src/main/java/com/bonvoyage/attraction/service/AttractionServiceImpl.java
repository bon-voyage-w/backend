package com.bonvoyage.attraction.service;

import com.bonvoyage.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.attraction.repository.AttractionInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {

    private AttractionInfoRepository attractionInfoRepository;

    @Override
    public List<AttractionInfoEntity> getAttractionList() {
        return attractionInfoRepository.findAll();
    }
}
