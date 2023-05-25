package com.bonvoyage.domain.notice.service;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import com.bonvoyage.domain.notice.entity.NoticeEntity;
import com.bonvoyage.domain.notice.repository.NoticeRepository;
import com.bonvoyage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserService userService;

    @Override
    public Page<NoticeDto> getNoticeList( int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize , Sort.Direction.DESC, "contentId");
        Page<NoticeEntity> noticePageEntityList = noticeRepository.findAll(page);
        return pageEntityListToPageDtoList(noticePageEntityList);
    }

    @Override
    public NoticeDto findByNoticeId(Long noticeId) {
        NoticeEntity noticeEntity = noticeRepository.findById(noticeId)
                .orElseThrow(NullPointerException::new);
        return noticeEntityToNoticeDto(noticeEntity);
    }

    @Override
    public Long addNotice(NoticeDto noticeDto) {
        noticeRepository.save(noticeDtoToNoticeEntity(noticeDto));
        return noticeDto.getNoticeId();
    }

    @Override
    public Long modifyNotice(Long noticeId, NoticeDto noticeDto) {
        NoticeEntity noticeEntity = noticeRepository.findById(noticeId)
                .orElseThrow(NullPointerException::new);
        noticeEntity.update(noticeDto.getTitle(), noticeDto.getContent());
        return noticeId;

    }

    @Override
    public Long removeNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
        return noticeId;
    }

    private Page<NoticeDto> pageEntityListToPageDtoList(Page<NoticeEntity> noticePageNoticeEntity) {
        Page<NoticeDto> noticePageDtoList = noticePageNoticeEntity.map(noticeEntity -> NoticeDto.builder()
                .noticeId(noticeEntity.getNoticeId())
                .title(noticeEntity.getTitle())
                .date(noticeEntity.getDate())
                .content(noticeEntity.getContent())
                .hit(noticeEntity.getHit())
                .userId(noticeEntity.getUserId())
                .build());
        return noticePageDtoList;
    }

    private NoticeDto noticeEntityToNoticeDto(NoticeEntity noticeEntity) {
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

    private NoticeEntity noticeDtoToNoticeEntity(NoticeDto noticeDto) {
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
