package com.bonvoyage.domain.notice.service;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import com.bonvoyage.domain.notice.entity.NoticeEntity;

import java.util.List;

public interface NoticeService {

    List<NoticeDto> getNoticeList();
    NoticeDto findByNoticeId(Long noticeId);
    Long addNotice(NoticeDto noticeDto);
    Long modifyNotice(Long noticeId, NoticeDto noticeDto);
    Long removeNotice(Long noticeId);

    default NoticeDto entityToDto(NoticeEntity noticeEntity) {
        NoticeDto noticeDto = NoticeDto.builder()
                .noticeId(noticeEntity.getNoticeId())
                .title(noticeEntity.getTitle())
                .date(noticeEntity.getDate())
                .content(noticeEntity.getContent())
                .hit(noticeEntity.getHit())
                .userId(noticeEntity.getUserId())
                .build();
        return noticeDto;
    }

    default NoticeEntity dtoToEntity(NoticeDto noticeDto) {
        NoticeEntity noticeEntity = NoticeEntity.builder()
                .noticeId(noticeDto.getNoticeId())
                .title(noticeDto.getTitle())
                .date(noticeDto.getDate())
                .content(noticeDto.getContent())
                .hit(noticeDto.getHit())
                .userId(noticeDto.getUserId())
                .build();
        return noticeEntity;
    }
}
