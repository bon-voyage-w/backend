package com.bonvoyage.domain.notice.service;

import com.bonvoyage.domain.notice.dto.NoticeDto;

import java.util.List;

public interface NoticeService {

    List<NoticeDto> getNoticeList();
    NoticeDto findByNoticeId(Long noticeId);
    void addNotice(NoticeDto noticeDto);
    Long modifyNotice(Long noticeId, NoticeDto noticeDto);
    void removeNotice(Long noticeId);
}
