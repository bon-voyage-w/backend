package com.bonvoyage.domain.route.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="route")
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    private int routeSeq;
    private int userId;

    @Builder
    public RouteEntity(Long routeId, int routeSeq, int userId) {
        this.routeId = routeId;
        this.routeSeq = routeSeq;
        this.userId = userId;
    }
}
