package com.bonvoyage.domain.notice.controller;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import com.bonvoyage.domain.notice.service.NoticeService;
import com.bonvoyage.domain.paging.dto.PageRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Notice Controller", description = "공지사항 컨트롤러")
@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "getNoticeList", description = "공지사항 전체 리스트 가져오기")
    @GetMapping("")
    public ResponseEntity<?> getNoticeList(PageRequestDto pageRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.getNoticeList(pageRequestDto));
    }

    @Operation(summary = "registerNotice", description = "공지사항 등록하기")
    @Parameter(name = "noticeDto", description = "공지사항 글 전체 내용")
    @PostMapping("")
    public ResponseEntity<Long> registerNotice(@RequestBody NoticeDto noticeDto){
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.addNotice(noticeDto));
    }
    
    @Operation(summary = "findByNoticeId", description = "공지사항 내용 상세보기")
    @Parameter(name = "noticeId", description = "공지사항 글 고유 번호")
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeDto> findByNoticeId(@PathVariable("noticeId") Long noticeId){
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.findByNoticeId(noticeId));
    }

    @Operation(summary = "modifyNotice", description = "공지사항 수정하기")
    @Parameter(name = "noticeId", description = "공지사항 글 고유 번호")
    @Parameter(name = "noticeDto", description = "공지사항 글 전체 내용")
    @PutMapping("/{noticeId}")
    public ResponseEntity<Long> modifyNotice(@PathVariable("noticeId") Long noticeId, @RequestBody NoticeDto noticeDto){
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.modifyNotice(noticeId, noticeDto));
    }

    @Operation(summary = "deleteNotice", description = "공지사항 삭제하기")
    @Parameter(name = "noticeId", description = "공지사항 글 고유 번호")
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Long> deleteNotice(@PathVariable("noticeId") Long noticeId) {
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.removeNotice(noticeId));
    }

}
