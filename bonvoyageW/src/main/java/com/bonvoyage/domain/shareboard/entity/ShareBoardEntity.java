package com.bonvoyage.domain.shareboard.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="share_board")
public class ShareBoardEntity {

    @Id
    private long share_board_id;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdTime;
    private int user_id;
    private int route_id;


    @Builder
    public ShareBoardEntity(long share_board_id, String title, String content, int hit,LocalDateTime createdTime, int user_id, int route_id) {
        this.share_board_id = share_board_id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdTime=createdTime;
        this.user_id = user_id;
        this.route_id = route_id;
    }
}
