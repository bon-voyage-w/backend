package com.bonvoyage.domain.notice.controller;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import com.bonvoyage.domain.notice.service.NoticeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("")
    public String getNoticeList() {
        return "";
    }

    @PostMapping("")
    public String registerNotice(){
        return "";
    }

    @GetMapping("/{noticeId}")
    public String findNoticeDetails(){
        return "";
    }

    @PutMapping("/{noticeId}")
    public String modifyNotice(){
        return "";
    }

    @DeleteMapping("/{noticeId}")
    public String deleteNotice() {
        return "";
    }

}
