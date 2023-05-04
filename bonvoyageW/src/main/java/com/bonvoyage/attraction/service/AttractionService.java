package com.bonvoyage.attraction.service;

import com.bonvoyage.attraction.dto.AttractionInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AttractionService {

    List<AttractionInfoDto> getAttractionList();

}
