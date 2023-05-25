package com.bonvoyage.domain.notice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "공지사항 글 고유 번호", required = true)
    private Long noticeId;
    @Schema(description = "공지사항 제목", required = true)
    private String title;
    @Schema(description = "공지사항 작성 날짜", required = true)
    private Date date;
    @Schema(description = "공지사항 내용", required = true)
    private String content;
    @Schema(description = "공지사항 조회수")
    private int hit;
    @Schema(description = "공지사항 작성자 고유 번호")
    private int userId;
}
