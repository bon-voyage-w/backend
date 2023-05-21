package com.bonvoyage.domain.shareboard.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="share_board_comments")
public class ShareBoardCommentEntity {

    @Id
    private long shareBoardCommentId;
    private String content;
    LocalDateTime createdTime;
    @ManyToOne @JoinColumn(name="share_board_id") @OnDelete(action = OnDeleteAction.CASCADE)
    ShareBoardEntity shareBoard;

    @Builder
    public ShareBoardCommentEntity(long shareBoardCommentId, String content, LocalDateTime createdTime, ShareBoardEntity shareBoard) {
        this.shareBoardCommentId = shareBoardCommentId;
        this.content = content;
        this.createdTime = createdTime;
        this.shareBoard = shareBoard;
    }
}
