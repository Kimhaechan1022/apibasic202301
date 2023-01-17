package com.example.apibasic.post.repository;

// 게시물 데이터를 CRUD -> 생성 조회 수정 삭제

import com.example.apibasic.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;


//@Component // 이 클래스로 만든 객체는 스프링이 관리를 해줘라(객체의 라이프사이클 관리를)
// new PostRepository() 이거 주입을 따로 스프링이 해줌, DI 의존성주입 디자인 패턴
 // 빈등록을 수행하며 저장소 개념까지 수행됨 @Component의 상위 버전
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
