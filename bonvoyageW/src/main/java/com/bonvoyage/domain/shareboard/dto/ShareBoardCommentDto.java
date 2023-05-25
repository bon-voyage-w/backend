package com.bonvoyage.domain.shareboard.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShareBoardCommentDto {
    @Builder
    public ShareBoardCommentDto(int shareBoardId, String content, LocalDateTime createdTime) {
        this.shareBoardId = shareBoardId;
        this.content = content;
        this.createdTime = createdTime;
    }

    int shareBoardId;
    String content;
    LocalDateTime createdTime;
}
