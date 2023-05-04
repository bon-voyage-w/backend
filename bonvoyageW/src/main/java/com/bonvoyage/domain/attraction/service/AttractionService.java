package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;

import java.util.List;

public interface AttractionService {

    List<AttractionInfoDto> getAttractionList();
    AttractionInfoDto view(long contentId);

}
