package com.bonvoyage.domain.route.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="route")
public class RouteDetailEntity {
    @Id
    private long routeDetailId;
    private int routeId;
    private int daySeq;
    @Column(name="content_io")
    private int routeContent;

    @Builder
    public RouteDetailEntity(long routeDetailId, int routeId,int daySeq, int routeContent) {
        this.routeDetailId = routeDetailId;
        this.routeId = routeId;
        this.daySeq=daySeq;
        this.routeContent = routeContent;
    }
}
