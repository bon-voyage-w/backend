package com.bonvoyage.domain.attraction.specification;

import com.bonvoyage.domain.attraction.entity.AttractionInfoEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AttractionSpecification {
//  지역(시도, 구군), 분류, 이름, 현재 위치(위도, 경도) 반경 5km
//   시도
    public static Specification<AttractionInfoEntity> equalSido(int sidoCode) {
        return new Specification<AttractionInfoEntity>() {
            @Override
            public Predicate toPredicate(Root<AttractionInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("sidoCode"), sidoCode);
            }
        };
    }
//  구군
    public static Specification<AttractionInfoEntity> equalGugun(int gugunCode) {
        return new Specification<AttractionInfoEntity>() {
            @Override
            public Predicate toPredicate(Root<AttractionInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("gugunCode"), gugunCode);
            }
        };
    }

//    분류
    public static Specification<AttractionInfoEntity> equalContentTypeId(Long contentTypeId) {
        return new Specification<AttractionInfoEntity>() {
            @Override
            public Predicate toPredicate(Root<AttractionInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("contentTypeId"), contentTypeId);
            }
        };
    }
//    검색어(이름)
    public static Specification<AttractionInfoEntity> containingTitle(String keyword) {
        return new Specification<AttractionInfoEntity>() {
            @Override
            public Predicate toPredicate(Root<AttractionInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("title"), "%" + keyword + "%");
            }
        };
    }
//    실시간 위치 기반

}
