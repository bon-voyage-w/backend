package com.bonvoyage.domain.notice.service;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import com.bonvoyage.domain.notice.entity.NoticeEntity;
import com.bonvoyage.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeDto> getNoticeList() {
        List<NoticeDto> result = new ArrayList<>();
        List<NoticeEntity> entityList = noticeRepository.findAll();

        for(NoticeEntity noticeEntity : entityList) {
            NoticeDto noticeDto = entityToDto(noticeEntity);
            result.add(noticeDto);
        }

        return result;
    }

    @Override
    public NoticeDto findByNoticeId(Long noticeId) {
        NoticeEntity noticeEntity = noticeRepository.findById(noticeId)
                .orElseThrow(NullPointerException::new);
        NoticeDto result = entityToDto(noticeEntity);

        return result;
    }

    @Override
    public Long addNotice(NoticeDto noticeDto) {
        noticeRepository.save(dtoToEntity(noticeDto));
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

}
