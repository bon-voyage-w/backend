package com.bonvoyage.domain.attraction.controller;

import com.bonvoyage.domain.attraction.dto.AttractionDetailPageInfoDto;
import com.bonvoyage.domain.attraction.dto.AttractionInfoDto;
import com.bonvoyage.domain.attraction.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Attraction Controller", description = "관광지 정보 컨트롤러")
@RestController
@RequestMapping("/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;
    
    @Operation(summary = "getAttractionList", description = "관광지 전체 리스트 가져오기")
    @GetMapping("")
    public ResponseEntity<?> getAttractionList() {
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.getAttractionList());
    }

    @Operation(summary = "findByContentId", description = "관광지 정보 상세보기")
    @Parameter(name = "contentId", description = "관광지 고유 번호")
    @GetMapping("/{contentId}")
    public ResponseEntity<AttractionDetailPageInfoDto> findByContentId(@PathVariable("contentId") Long contentId) {
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.findByContentId(contentId));
    }

    @Operation(summary = "searchByParams", description = "여러 검색어를 적용하여 관광지 리스트 가져오기 : 검색어, 시도, 구군, 관광지 종류")
    @Parameter(name = "keyword", description = "검색어")
    @Parameter(name = "sidoCode", description = "시도 고유 번호")
    @Parameter(name = "gugunCode", description = "구군 고유 번호")
    @Parameter(name = "contentTypeId", description = "관광지 분류 번호")
    @GetMapping("/search")
    public ResponseEntity<?> searchByParams(@RequestParam(required = false) String keyword,
                                                                  @RequestParam(required = false, defaultValue="0") int sidoCode,
                                                                  @RequestParam(required = false, defaultValue="0") int gugunCode,
                                                                  @RequestParam(required = false, defaultValue="0") int contentTypeId) {
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.findSearch(keyword, sidoCode, gugunCode, contentTypeId));
    }

}
