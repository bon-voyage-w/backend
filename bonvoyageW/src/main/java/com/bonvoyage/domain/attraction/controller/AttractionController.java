package com.bonvoyage.domain.attraction.controller;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("/attractionList")
    public List<AttractionInfoDto> list() {
//        List<AttractionInfoEntity> attractions = attractionService.getAttractionList();
//        model.addAttribute("attractions", attractions);


        return attractionService.getAttractionList();
    }

}
