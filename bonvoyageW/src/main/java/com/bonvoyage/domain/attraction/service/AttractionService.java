package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;

import java.util.List;

public interface AttractionService {

    List<AttractionInfoDto> getAttractionList();
    AttractionDetailPageInfoDto findByContentId(Long contentId);

//    List<AttractionInfoDto> findByTitle(String title);
    List<AttractionInfoDto> findSearch(String keyword, int sidoCode, int gugunCode, int contentTypeId);

}
