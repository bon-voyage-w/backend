package com.bonvoyage.domain.notice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notices")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    private String title;
    @CreatedDate
    private Date date;
    private String content;
    private int hit;
    private int userId;

    @Builder
    public NoticeEntity(Long noticeId, String title, Date date, String content, int hit, int userId) {
        this.noticeId = noticeId;
        this.title = title;
        this.date = date;
        this.content = content;
        this.hit = hit;
        this.userId = userId;
    }

    /* 게시글 수정 */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
