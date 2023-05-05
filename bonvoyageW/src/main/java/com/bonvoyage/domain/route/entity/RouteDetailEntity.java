package com.bonvoyage.domain.route.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="route")
public class RouteDetailEntity {
    @Id
    private long routeDetailId;
    private int routeId;
    private int userId;
}
