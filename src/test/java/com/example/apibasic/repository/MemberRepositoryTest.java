package com.example.apibasic.repository;

// jUnit5 에서는 클래스, 메서드, 필드 default 제한만 허용
// public , protected이런거 하면안댐

import com.example.apibasic.jpabasic.entity.Gender;
import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest // 스프링 컨테이너를 사용해서 스프링이 관리하는 객체를 주입받는 기능 활성화
class MemberRepositoryTest {

    // 스프링 빈을 주입받을 때 필드 주입을 사용함

    @Autowired
    MemberRepository memberRepository;

    // 테스트 메서드
    // 단언 (Assertion) : 강력히 주장함
    @Test
    @DisplayName("회원의 가입 정보를 데이터베이스에 저장 해야한다.") // 저장할지도? 저장할걸 원한다 이딴식의 멘트면 안댐
    @Transactional
    @Rollback // 테스트가 끝나면 이전상태로 롤백
    void saveTest() {
        // given - when - then 패턴
        // given : 테스트시 주어지는 데이터
        MemberEntity saveMember = MemberEntity.builder()
                .account("zzz1234")
                .password("1234")
                .nickName("꾸러긔")
                .gender(Gender.FEMALE)
                .build();
        // when : 실제 테스트 상황
        memberRepository.save(saveMember); // insert쿼리 실행

        Optional<MemberEntity> foundMember = memberRepository.findById(1L);// pk기반 단일 행 조회

        // then : 테스트 결과 단언
        // 회원이 조회되었을 것이다.
        MemberEntity member = foundMember.get();
        assertNotNull(member);

        // 그 저장된 회원의 user_code는 몇번? => 1번
        // param1: 내 기대값,  param2: 실제값
//        assertEquals(1L, member.getUserId());

        // 저장된 회원의 닉네임은 뭘까요?  => 꾸러긔
        assertEquals("꾸러긔", member.getNickName());
    }



    // 목록조회
    @Test
    @DisplayName("회원 목록을 조회하면 3명의 회원정ㅂ고가 조회되어야 한다.")
    @Transactional
    @Rollback
    void findAllTest(){
        MemberEntity saveMember1 = MemberEntity.builder()
                .account("zz1234")
                .password("123")
                .nickName("꾸러긔")
                .gender(Gender.FEMALE)
                .build();
        MemberEntity saveMember2 = MemberEntity.builder()
                .account("asdv214")
                .password("123")
                .nickName("귱얘")
                .gender(Gender.MALE)
                .build();
        MemberEntity saveMember3 = MemberEntity.builder()
                .account("hsdg1343")
                .password("123")
                .nickName("꾸러2긔")
                .gender(Gender.FEMALE)
                .build();

        //when

        memberRepository.save(saveMember1);
        memberRepository.save(saveMember2);
        memberRepository.save(saveMember3);

        List<MemberEntity> memberEntityList = memberRepository.findAll();

        //then

        // 조회된 리스트의 사이즈는 3이어야한다.
        assertEquals(3, memberEntityList.size());

        // 조회된 리스트의 2번째 회원 닉네임은 귱얘여얗ㅁ
        assertEquals("귱얘", memberEntityList.get(1).getNickName());


    }



}