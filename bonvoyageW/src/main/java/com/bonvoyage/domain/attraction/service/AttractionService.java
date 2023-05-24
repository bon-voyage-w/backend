package com.bonvoyage.domain.attraction.service;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.dto.GugunDto;
import com.bonvoyage.domain.attraction.dto.SidoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AttractionService {

    Page<AttractionInfoDto> getAttractionList(int pageNumber, int pageSize);
    AttractionDetailPageInfoDto findByContentId(Long contentId);

//    List<AttractionInfoDto> findByTitle(String title);
Page<AttractionInfoDto> findSearch(String keyword, int sidoCode, int gugunCode, int contentTypeId, int pageNumber, int pageSize);
List<SidoDto> getSidoList();
List<GugunDto> getGugunList(Long sidoCode);
}
