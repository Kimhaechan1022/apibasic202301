package com.example.apibasic.repository;

import com.example.apibasic.jpabasic.entity.Gender;
import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

// JPA로 CRUD Operation을 수행 하려면 JPA레포지토리 인터페이스를 상송
// 제너릭 타입으로 첫번째로 CRUD할 엔터티 클래스의 타입, 두번째로 해당 엔터티의 id의 타입
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 구현체 자동생성됨, 프록시 클래스

    // 쿼리 메서드 사용
    List<MemberEntity> findByGender(Gender gender);
    List<MemberEntity> findByAccountAndGender(String account, Gender gender);

    List<MemberEntity> findByNickNameContaining(String nickName);



    // jpql 사용
    // select 별칭 from 엔터티 클래스명 as 별칭 where 별칭.필드명
    /**
     *  native-sql :  select m.user_code  from tbl_member as m
     *  jpql :  select m.userId  from MemberEntity as m
     */

    // 계정명으로 회원 조회
    // 숫자 방식 매핑, ?
    @Query("select m from MemberEntity as m where m.account=?1")
    MemberEntity getMemberByAccount(String account);

    // 문자 방식 매핑, :
    @Query("select m from MemberEntity as m where m.nickName=:nick and m.gender=:gen")
    List<MemberEntity> getMemberByNickAndGender(String nick, Gender gen);

    @Query("select m from MemberEntity as m where m.nickName like %:nick%")
    List<MemberEntity> getMemberByNickName(String nick);

    @Transactional
    @Modifying
    @Query("delete from MemberEntity as m where m.nickName=:nick")
    void deleteByNicK(@Param("nick") String nick);






}
