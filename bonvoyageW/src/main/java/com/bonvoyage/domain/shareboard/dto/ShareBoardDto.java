package com.bonvoyage.domain.shareboard.dto;

import lombok.Data;

@Data
public class ShareBoardDto {

    private int share_board_id;
    private String title;
    private String content;
    String writerName;
    String writerLoginId;
    private int route_id;
}
