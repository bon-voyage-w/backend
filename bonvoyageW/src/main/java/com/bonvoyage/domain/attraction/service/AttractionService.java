package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AttractionService {

    Page<AttractionInfoDto> getAttractionList();
    AttractionDetailPageInfoDto findByContentId(Long contentId);

//    List<AttractionInfoDto> findByTitle(String title);
    Page<AttractionInfoDto> findSearch(String keyword, int sidoCode, int gugunCode, int contentTypeId);

}
