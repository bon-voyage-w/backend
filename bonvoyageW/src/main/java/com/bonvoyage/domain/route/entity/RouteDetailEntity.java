package com.bonvoyage.domain.route.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="route_detail")
public class RouteDetailEntity {
    @Id
    private long routeDetailId;

    private int routeId;
    private int daySeq;
    private int visitSeq;
    @Column(name="content_id")
    private int routeContent;

    @Builder
    public RouteDetailEntity(long routeDetailId, int routeId,int daySeq,int visitSeq, int routeContent) {
        this.routeDetailId = routeDetailId;
        this.routeId = routeId;
        this.visitSeq=visitSeq;
        this.daySeq=daySeq;
        this.routeContent = routeContent;
    }
}
