package com.bonvoyage.domain.attraction.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gugun")
public class GugunEntity {
    @Id
    private Long gugunCode;
    private String gugunName;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "sidoCode")
    private SidoEntity sidoEntity;

    @Builder
    public GugunEntity(Long gugunCode, String gugunName) {
        this.gugunCode = gugunCode;
        this.gugunName = gugunName;
    }
}
