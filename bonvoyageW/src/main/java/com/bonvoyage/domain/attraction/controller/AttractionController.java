package com.bonvoyage.domain.attraction.controller;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("")
    public ResponseEntity<List<AttractionInfoDto>> getAttractionList() {
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.getAttractionList());
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<AttractionDetailPageInfoDto> findByContentId(@PathVariable("contentId") Long contentId) {
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.findByContentId(contentId));
    }

    @GetMapping("/{contentTypeId}")
    public ResponseEntity<List<AttractionInfoDto>> findByContentTypeId(@PathVariable("contentTypeId") Long contentTypeId) {
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.findByContentTypeId(contentTypeId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AttractionInfoDto>> findByTitle(@RequestParam String title) {
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.findByTitle(title));
    }

}
