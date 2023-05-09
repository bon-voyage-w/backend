package com.bonvoyage.domain.notice.controller;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import com.bonvoyage.domain.notice.service.NoticeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("")
    public List<NoticeDto> getNoticeList() {
        return noticeService.getNoticeList();
    }

    @PostMapping("")
    public String registerNotice(@RequestBody NoticeDto noticeDto){
        noticeService.addNotice(noticeDto);
        return "";
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
