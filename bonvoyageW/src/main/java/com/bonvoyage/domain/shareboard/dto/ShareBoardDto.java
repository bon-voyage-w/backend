package com.bonvoyage.domain.shareboard.dto;

import com.bonvoyage.domain.route.dto.RouteDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShareBoardDto {



    private int shareBoardId;
    private String title;
    private String content;
    String writerName;
    String writerLoginId;
    private int routeId;
    RouteDto routeDto;
    LocalDateTime createdTime;
    List<comments> commentsList;
    @Data
    public static class comments{
        @Builder
        public comments(int commentId,String commentWriterName, String commentWriterLoginId, String commentContent, LocalDateTime commentCreatedTime) {
            this.commentId=commentId;
            this.commentWriterName = commentWriterName;
            this.commentWriterLoginId = commentWriterLoginId;
            this.commentContent = commentContent;
            this.commentCreatedTime = commentCreatedTime;
        }
        int commentId;
        String commentWriterName;
        String commentWriterLoginId;
        String commentContent;
        LocalDateTime commentCreatedTime;
    }
    @Builder
    public ShareBoardDto(int shareBoardId, String title, String content, String writerName, String writerLoginId, int routeId, RouteDto routeDto) {
        this.shareBoardId = shareBoardId;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.writerLoginId = writerLoginId;
        this.routeId = routeId;
        this.routeDto = routeDto;
    }
}
