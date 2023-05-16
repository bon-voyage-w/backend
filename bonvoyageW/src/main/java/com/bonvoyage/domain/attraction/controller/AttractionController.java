package com.bonvoyage.domain.attraction.controller;

import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("")
    public List<AttractionInfoDto> list() {
        return attractionService.getAttractionList();
    }

    @GetMapping("/{contentId}")
    public AttractionInfoDto view(@PathVariable("contentId") long contentId) {
        return attractionService.findByContentId(contentId);
    }

    @GetMapping("/search")
    public List<AttractionInfoDto> findByTitle(@RequestParam String title) {
        return attractionService.findByTitle(title);
    }

}
