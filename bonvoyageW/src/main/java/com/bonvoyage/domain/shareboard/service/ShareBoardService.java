package com.bonvoyage.domain.shareboard.service;

import com.bonvoyage.domain.shareboard.dto.ShareBoardCommentDto;
import com.bonvoyage.domain.shareboard.dto.ShareBoardDto;

public interface ShareBoardService {
    int addShareBoard(int userId, ShareBoardDto shareBoardDto);

    ShareBoardDto findShareBoardDetailById(int shareBoardId);

    void removeShareBoard(int userId, int shareBoardId);

    void addComment(int userId, ShareBoardCommentDto shareBoardCommentDto);

    void modifyShareBoard(int userId, int shareBoardId, ShareBoardDto shareBoardDto);

    int removeComment(int userId, int commentId);
}
