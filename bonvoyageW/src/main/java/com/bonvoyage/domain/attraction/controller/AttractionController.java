package com.bonvoyage.domain.attraction.controller;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("/")
    public List<AttractionInfoDto> list() {
        return attractionService.getAttractionList();
    }

    @GetMapping("/{contentId}")
    public AttractionInfoDto view(@PathVariable("contentId") int contentId) {
        System.out.println("@@@@@@@@@@@ c " + contentId);
        return attractionService.view((long)contentId);
    }

}
