package com.bonvoyage.domain.notice.service;

import com.bonvoyage.domain.notice.dto.NoticeDto;

import java.util.List;

public interface NoticeService {

    List<NoticeDto> getNoticeList();
    NoticeDto findByNoticeId(long noticeId);
    void addNotice(NoticeDto noticeDto);
    void modifyNotice(NoticeDto noticeDto);
    void removeNotice(long noticeId);
}
