package com.bonvoyage.attraction.controller;

import com.bonvoyage.attraction.dto.AttractionInfoDto;
import com.bonvoyage.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attractions")
//@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @Autowired
    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping("/attractionList")
    public List<AttractionInfoDto> list() {
//        List<AttractionInfoEntity> attractions = attractionService.getAttractionList();
//        model.addAttribute("attractions", attractions);


        return attractionService.getAttractionList();
    }

}
