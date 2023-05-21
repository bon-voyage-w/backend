package com.bonvoyage.domain.route.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="route")
public class RouteEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;
    private int totalDays;
    private int userId;
    private String routeTitle;
    @CreationTimestamp
    private LocalDateTime createdTime;


    @Builder
    public RouteEntity(Long routeId, int totalDays, int userId, String routeTitle, LocalDateTime createdTime) {
        this.routeId = routeId;
        this.totalDays = totalDays;
        this.userId = userId;
        this.routeTitle = routeTitle;
        this.createdTime = createdTime;
    }
}
