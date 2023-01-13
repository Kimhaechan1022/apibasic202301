package com.example.apibasic.post.repository;

// 게시물 데이터를 CRUD -> 생성 조회 수정 삭제

import com.example.apibasic.post.entity.PostEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;


//@Component // 이 클래스로 만든 객체는 스프링이 관리를 해줘라(객체의 라이프사이클 관리를)
// new PostRepository() 이거 주입을 따로 스프링이 해줌, DI 의존성주입 디자인 패턴
@Repository // 빈등록을 수행하며 저장소 개념까지 수행됨 @Component의 상위 버전
public class PostRepository {

    // 메모리 저장소
    private static final Map<Long, PostEntity> posts = new HashMap<>();

    public List<PostEntity> findAll(){
        List<PostEntity> postEntityList = new ArrayList<>();
//        posts.forEach(());

        Set<Long> keySet = posts.keySet();
        for (Long postNo : keySet) {
            PostEntity postEntity = posts.get(keySet);
            postEntityList.add(postEntity);
        }
        return postEntityList;
    }

    // 개별조회
    public PostEntity findOne(Long postNo){
        return posts.get(postNo);
    }

    // 게시물 등록, 수정
    // key의 중복이 존재할경우 수정기능이 바로 수행되는 map의 특성이 있다.
    public boolean save(PostEntity postEntity){
        PostEntity post = posts.put(postEntity.getPostNo(), postEntity);
        return post != null;
    }

    // 게시물 삭제
    public boolean delete(Long postNo){
        PostEntity remove = posts.remove(postNo);
        return remove !=null;
    }

}
