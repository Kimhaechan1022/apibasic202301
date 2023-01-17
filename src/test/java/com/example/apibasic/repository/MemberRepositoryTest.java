package com.example.apibasic.repository;

// jUnit5 에서는 클래스, 메서드, 필드 default 제한만 허용
// public , protected이런거 하면안댐

import com.example.apibasic.jpabasic.entity.Gender;
import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 컨테이너를 사용해서 스프링이 관리하는 객체를 주입받는 기능 활성화
class MemberRepositoryTest {

    // 스프링 빈을 주입받을 때 필드 주입을 사용함

    @Autowired
    MemberRepository memberRepository;

    // @BeforeEach - 각 테스트를 실행하기 전에 실행되는 내용
    @BeforeEach
    @Transactional
    @Rollback
    void bulkInsert(){
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
        MemberEntity saveMember4 = MemberEntity.builder()
                .account("g13efa43")
                .password("123")
                .nickName("꾸러23긔")
                .gender(Gender.MALE)
                .build();
        MemberEntity saveMember5 = MemberEntity.builder()
                .account("sdaefg3")
                .password("123")
                .nickName("꾸러24긔")
                .gender(Gender.FEMALE)
                .build();
        //when

        memberRepository.save(saveMember1);
        memberRepository.save(saveMember2);
        memberRepository.save(saveMember3);
        memberRepository.save(saveMember4);
        memberRepository.save(saveMember5);
    }

    // 테스트 메서드
    // 단언 (Assertion) : 강력히 주장함
//    @Test
//    @DisplayName("회원의 가입 정보를 데이터베이스에 저장 해야한다.") // 저장할지도? 저장할걸 원한다 이딴식의 멘트면 안댐
//    @Transactional
//    @Rollback // 테스트가 끝나면 이전상태로 롤백
//    void saveTest() {
//        // given - when - then 패턴
//        // given : 테스트시 주어지는 데이터
//
//
//        Optional<MemberEntity> foundMember = memberRepository.findById(1L);// pk기반 단일 행 조회
//
//        // then : 테스트 결과 단언
//        // 회원이 조회되었을 것이다.
//        MemberEntity member = foundMember.get();
//        assertNotNull(member);
//
//        // 그 저장된 회원의 user_code는 몇번? => 1번
//        // param1: 내 기대값,  param2: 실제값
////        assertEquals(1L, member.getUserId());
//
//        // 저장된 회원의 닉네임은 뭘까요?  => 꾸러긔
//        assertEquals("꾸러긔", member.getNickName());
//    }



    // 목록조회
    @Test
    @DisplayName("회원 목록을 조회하면 3명의 회원정ㅂ고가 조회되어야 한다.")
    @Transactional
    @Rollback
    void findAllTest(){

        List<MemberEntity> memberEntityList = memberRepository.findAll();

        //then

        // 조회된 리스트의 사이즈는 3이어야한다.
        assertEquals(3, memberEntityList.size());

        // 조회된 리스트의 2번째 회원 닉네임은 귱얘여얗ㅁ
        assertEquals("귱얘", memberEntityList.get(1).getNickName());

    }

    @Test
    @DisplayName("회원 데이터를 3개 등록하고 그 중 하나의 회원을 삭제해야 한다.")
    @Transactional
    @Rollback
    void deleteTest() {
        // given

        Long userCode = 2L;
        // when
        memberRepository.deleteById(userCode);
        Optional<MemberEntity> foundMember = memberRepository.findById(userCode);

        // then
        assertFalse(foundMember.isPresent());
        assertEquals(2, memberRepository.findAll().size());

    }

    @Test
    @DisplayName("수정 테스트")
    @Transactional
    @Rollback
    void modifyTest(){
        // given
        Long userCode = 2L;
        String newNickName = "닭강정";
        Gender newGender = Gender.FEMALE;


        List<MemberEntity> testList = memberRepository.findAll();
        int a=1;

        Optional<MemberEntity> foundMember = memberRepository.findById(userCode);
        foundMember.ifPresent(m->{
            m.setNickName(newNickName);
            m.setGender(newGender);
        });

        Optional<MemberEntity> modMember = memberRepository.findById(userCode);
        assertEquals(modMember.get().getNickName(), newNickName);
        assertEquals(modMember.get().getGender(), newGender);

    }

    @Test
    @DisplayName("쿼리 메소드를 사용하여 여성회원만 조회 한다")
    void findByGenderTest(){
        // given
        Gender gender = Gender.FEMALE;

        // when
        List<MemberEntity> list= memberRepository.findByGender(gender);

        // then s
        list.forEach(m -> {
            System.out.println(m);
            assertTrue(m.getGender() == Gender.FEMALE);
        });
    }
    @Test
    @DisplayName("쿼리메서드를 사용하여 계정명이 asdv214  남성인 회원만 조회해야 한다.")
    void findByAccountAndGenderTest() {
        // given
        String account = "abc4321";
        Gender gender = Gender.MALE;
        // when
        List<MemberEntity> list = memberRepository.findByAccountAndGender(account, gender);
        // then
        list.forEach(m -> {
            System.out.println(m);
            assertTrue(m.getGender() == Gender.MALE);
            assertEquals("asdv214", m.getAccount());
        });
    }

    @Test
    @DisplayName("쿼리메서드를 사용하여 이름에 '꾸'이 포함된 회원 조회")
    void findByContainingTest() {

        // given
        String nickName= "꾸";

        // when
        List<MemberEntity> list
                = memberRepository.findByNickNameContaining(nickName);
        // then
        list.forEach(m -> {
            System.out.println(m);
            assertTrue(m.getNickName().contains(nickName));
        });
    }


    @Test
    @DisplayName("jpql을 사용해서 계정명이 g13efa43인 멤버 조회 ")
    void jpqlTest1() {

        // given
        String acc= "g13efa43";

        //when
        MemberEntity member = memberRepository.getMemberByAccount(acc);
        // then
        System.out.println("member = " + member);

        assertEquals("꾸러23긔", member.getNickName());
    }


    @Test
    @DisplayName("jpql을 사용해서 별명과 성별로 조회")
    void jpqlTest2() {

        // given
        String nick= "꾸러23긔";
        Gender gen = Gender.MALE;

        //when
        List<MemberEntity> members = memberRepository.getMemberByNickAndGender(nick,gen);
//         then
        members.forEach(m -> {
                    System.out.println(m);
                    assertEquals("꾸러23긔", m.getNickName());
                    assertEquals(Gender.MALE, m.getGender());
                });
    }

    @Test
    @DisplayName("쿼리메서드를 사용하여 이름에 '꾸'이 포함된 회원 조회")
    void jpql3() {

        // given
        String nickName= "꾸";

        // when
        List<MemberEntity> list
                = memberRepository.getMemberByNickName(nickName);
        // then
        list.forEach(m -> {
            System.out.println(m);
            assertTrue(m.getNickName().contains(nickName));
        });
    }


}