package com.bonvoyage.domain.notice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeId;

    private String title;
    private Date date;
    private String content;
    private int hit;
    private int userId;

    @Builder
    public NoticeEntity(long noticeId, String title, Date date, String content, int hit, int userId) {
        this.noticeId = noticeId;
        this.title = title;
        this.date = date;
        this.content = content;
        this.hit = hit;
        this.userId = userId;
    }
}
