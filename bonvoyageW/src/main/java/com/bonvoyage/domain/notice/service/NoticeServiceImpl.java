package com.bonvoyage.domain.notice.service;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Override
    public List<NoticeDto> getNoticeList() {
        return null;
    }

    @Override
    public NoticeDto findByNoticeId(long noticeId) {
        return null;
    }

    @Override
    public void addNotice(NoticeDto noticeDto) {

    }

    @Override
    public void modifyNotice(NoticeDto noticeDto) {

    }

    @Override
    public void removeNotice(long noticeId) {

    }
}
