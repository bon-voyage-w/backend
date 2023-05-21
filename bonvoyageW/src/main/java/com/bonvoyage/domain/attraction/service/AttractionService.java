package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;

import java.util.List;

public interface AttractionService {

    List<AttractionInfoDto> getAttractionList();
//    AttractionInfoDto findByContentId(Long contentId);
    AttractionDetailPageInfoDto findByContentId(Long contentId);
    List<AttractionInfoDto> findByContentTypeId(Long contentTypeId);

    List<AttractionInfoDto> findByTitle(String title);

}
