package com.bonvoyage.domain.shareboard.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="share_board")
public class ShareBoardEntity {

    @Id
    private long shareBoardId;
    private String title;
    private String content;
    private int hit;
    @CreationTimestamp
    private LocalDateTime createdTime;
    private int userId;
    private int routeId;


    @Builder
    public ShareBoardEntity(int shareBoardId, String title, String content, int hit, LocalDateTime createdTime, int userId, int routeId) {
        this.shareBoardId= shareBoardId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdTime=createdTime;
        this.userId = userId;
        this.routeId = routeId;
    }
    public void updateShareBoard(String title, String content,int routeId){
        this.title=title;
        this.content=content;
        this.routeId=routeId;
    }
}
