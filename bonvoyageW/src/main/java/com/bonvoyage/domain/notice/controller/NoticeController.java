package com.bonvoyage.domain.notice.controller;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import com.bonvoyage.domain.notice.service.NoticeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("")
    public ResponseEntity<List<NoticeDto>> getNoticeList() {
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.getNoticeList());
    }

    @PostMapping("")
    public ResponseEntity<?> registerNotice(@RequestBody NoticeDto noticeDto){
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.addNotice(noticeDto));
    }

    @GetMapping("/{noticeId}")
    public NoticeDto findByNoticeId(@PathVariable("noticeId") Long noticeId){
        return noticeService.findByNoticeId(noticeId);
    }

    @PutMapping("/{noticeId}")
    public String modifyNotice(@PathVariable("noticeId") Long noticeId, @RequestBody NoticeDto noticeDto){
        noticeService.modifyNotice(noticeId, noticeDto);
        return "";
    }

    @DeleteMapping("/{noticeId}")
    public String deleteNotice(@PathVariable("noticeId") Long noticeId) {
        noticeService.removeNotice(noticeId);
        return "";
    }

}
