package com.bonvoyage.domain.attraction.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sido")
public class SidoEntity {

    @Id
    private Long sidoCode;
    private String sidoName;

    @Builder
    public SidoEntity(Long sidoCode, String sidoName) {
        this.sidoCode = sidoCode;
        this.sidoName = sidoName;
    }
}
