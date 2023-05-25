package com.bonvoyage.domain.shareboard.service;

import com.bonvoyage.domain.route.service.RouteService;
import com.bonvoyage.domain.shareboard.dto.ShareBoardCommentDto;
import com.bonvoyage.domain.shareboard.dto.ShareBoardDto;
import com.bonvoyage.domain.shareboard.entity.ShareBoardCommentEntity;
import com.bonvoyage.domain.shareboard.entity.ShareBoardEntity;
import com.bonvoyage.domain.shareboard.repository.ShareBoardCommentRepository;
import com.bonvoyage.domain.shareboard.repository.ShareBoardRepository;
import com.bonvoyage.domain.user.repository.UserRepository;
import com.bonvoyage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.ui.SpringDocUIException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ShareBoardServiceImpl implements ShareBoardService {
    private final ShareBoardRepository shareBoardRepository;
    private final ShareBoardCommentRepository shareBoardCommentRepository;
    private final RouteService routeService;
    private final UserRepository userRepository;
    @Override
    public int addShareBoard(int userId, ShareBoardDto shareBoardDto) {
        ShareBoardEntity shareBoardEntity=ShareBoardEntity.builder()
                .content(shareBoardDto.getContent())
                .shareBoardId(shareBoardDto.getShareBoardId())
                .userId(userId).build();
        ShareBoardEntity newShareBoardEntity=shareBoardRepository.save(shareBoardEntity);
        return (int) newShareBoardEntity.getShareBoardId();
    }

    @Override
    public ShareBoardDto findShareBoardDetailById(int shareBoardId) {
        ShareBoardEntity shareBoardEntity=shareBoardRepository.findById((long)shareBoardId)
                .orElseThrow(NoSuchElementException::new);
        ShareBoardDto shareBoardDto=ShareBoardDto.builder()
                .shareBoardId((int) shareBoardEntity.getShareBoardId())
                .content(shareBoardEntity.getContent())
                .title(shareBoardEntity.getTitle())
                .routeDto(routeService.findRouteDetail(shareBoardEntity.getRouteId()))
                .writerName(userRepository.getReferenceById((long) shareBoardEntity.getUserId()).getName())
                .writerLoginId(userRepository.getReferenceById((long) shareBoardEntity.getUserId()).getLoginId())
                .build();

        return shareBoardDto;
    }

    @Override
    public void removeShareBoard(int userId, int shareBoardId) {
        ShareBoardEntity shareBoardEntity=shareBoardRepository.findById((long)shareBoardId)
                .orElseThrow(NoSuchElementException::new);
        if(userId!=shareBoardEntity.getUserId()){
            throw new SecurityException();
        }
        shareBoardRepository.delete(shareBoardEntity);
    }

    @Override
    public void addComment(int userId, ShareBoardCommentDto shareBoardCommentDto) {
        ShareBoardEntity shareBoardEntity=shareBoardRepository.findById((long)shareBoardCommentDto.getShareBoardId())
                .orElseThrow(NoSuchElementException::new);
        ShareBoardCommentEntity commentEntity=ShareBoardCommentEntity.builder()
                .content(shareBoardCommentDto.getContent())
                .userId(userId)
                .shareBoard(shareBoardEntity).build();
        shareBoardCommentRepository.save(commentEntity);
    }

    @Override
    public void modifyShareBoard(int userId, int shareBoardId, ShareBoardDto shareBoardDto) {

        ShareBoardEntity shareBoardEntity=shareBoardRepository.findById((long)shareBoardId)
                .orElseThrow(NoSuchElementException::new);
        if(userId!=shareBoardEntity.getUserId()){
            throw new SecurityException();
        }
        shareBoardEntity.updateShareBoard(shareBoardDto.getTitle(), shareBoardDto.getContent(),shareBoardDto.getRouteId());
        return;
    }

    @Override
    public int removeComment(int userId, int commentId) {
        ShareBoardCommentEntity commentEntity=shareBoardCommentRepository.findById((long)commentId)
                .orElseThrow(NoSuchElementException::new);
        if(userId!=commentEntity.getUserId()){
            throw new SecurityException();
        }
        int boardId=commentEntity.getUserId();
        shareBoardCommentRepository.delete(commentEntity);
        return boardId;
    }
}
