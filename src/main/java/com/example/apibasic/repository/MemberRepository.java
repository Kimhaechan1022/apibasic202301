package com.example.apibasic.repository;

import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA로 CRUD Operation을 수행 하려면 JPA레포지토리 인터페이스를 상송
// 제너릭 타입으로 첫번째로 CRUD할 엔터티 클래스의 타입, 두번째로 해당 엔터티의 id의 타입
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 구현체 자동생성됨, 프록시 클래스
}
