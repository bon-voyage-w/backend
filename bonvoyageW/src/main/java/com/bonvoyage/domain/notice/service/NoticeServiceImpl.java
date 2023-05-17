package com.bonvoyage.domain.notice.service;

import com.bonvoyage.domain.notice.dto.NoticeDto;
import com.bonvoyage.domain.notice.entity.NoticeEntity;
import com.bonvoyage.domain.notice.repository.NoticeRepository;
import com.bonvoyage.domain.paging.dto.PageRequestDto;
import com.bonvoyage.domain.paging.dto.PageResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public PageResultDto<NoticeDto, NoticeEntity> getNoticeList(PageRequestDto pageRequestDto) {
//        List<NoticeDto> result = new ArrayList<>();
//        List<NoticeEntity> entityList = noticeRepository.findAll();
//
//        for(NoticeEntity noticeEntity : entityList) {
//            NoticeDto noticeDto = entityToDto(noticeEntity);
//            result.add(noticeDto);
//        }
//
//        return result;
        Pageable pageable = pageRequestDto.getPageable(Sort.by("noticeId"));
        Page<NoticeEntity> entityList = noticeRepository.findAll(pageable);
        Function<NoticeEntity, NoticeDto> fn = (noticeEntity -> entityToDto(noticeEntity));
        return new PageResultDto<>(entityList, fn);
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

//    @Override
//    public List<NoticeDto> getPagingList(PageRequestDto requestDto) {
//        Pageable pageable = requestDto.getPageable(Sort.by("noticeId"));
//        Page<NoticeEntity> entityList = noticeRepository.findAll(pageable);
//        Function<NoticeEntity, NoticeDto> fn = (noticeEntity -> entityToDto(noticeEntity));
//        return new PageResultDto<>(entityList, fn).getDtoList();
//    }

}
