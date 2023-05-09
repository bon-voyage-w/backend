package com.bonvoyage.domain.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    private Long noticeId;
    private String title;
    private Date date;
    private String content;
    private int hit;
    private int userId;
}
