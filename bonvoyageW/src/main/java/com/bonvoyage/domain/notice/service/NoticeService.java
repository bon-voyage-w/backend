package com.bonvoyage.domain.notice.service;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoticeService {

    Page<NoticeDto> getNoticeList(int pageNumber, int pageSize);
    NoticeDto findByNoticeId(Long noticeId);
    Long addNotice(NoticeDto noticeDto);
    Long modifyNotice(Long noticeId, NoticeDto noticeDto);
    Long removeNotice(Long noticeId);
}
