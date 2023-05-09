package com.bonvoyage.domain.notice.service;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import com.bonvoyage.domain.notice.entity.NoticeEntity;
import com.bonvoyage.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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
    public void addNotice(NoticeDto noticeDto) {
       noticeRepository.save(dtoToEntity(noticeDto));
    }

    @Override
    public void modifyNotice(Long noticeId, NoticeDto noticeDto) {
//        update : title, content
        NoticeEntity noticeEntity = noticeRepository.findById(noticeId)
                        .orElseThrow(NegativeArraySizeException::new);

        noticeRepository.save(dtoToEntity(noticeDto));
    }

    @Override
    public void removeNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    public NoticeDto entityToDto(NoticeEntity noticeEntity) {
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

    public NoticeEntity dtoToEntity(NoticeDto noticeDto) {
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
