package com.example.apibasic.post.api;


import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// resource : 게시물(post)

// uri : 컬렉션 형태로 복수형, posts
/**
 * 게시물 목록 조회 : /posts -get
 * 게시물 개별 조회 : /posts/{id} -get
 * 게시물 등록 : /posts -post
 * 게시물 수정 : /posts/{id} -patch
 * 게시물 삭제 : /posts/{id} -delete
 */


@RestController
@Slf4j
@RequiredArgsConstructor // 생성자 주입 생략 가능
@RequestMapping("/posts")
public class PostApiController {


    //    직접 객체를 생성할경의 의존하는 모든 클래스를 모두 수정해야하는 문제가 생길수 있다.
//    제어를 역전하는 의존성 주입을 수행한다. postRepository 를 안쓰고 다른걸 쓴다면? 다른 레파지토리를 다시 디자인 한다면?
//    수정이 극혐이 되기 때문에 의존성 주입한다. 의존하는 객체를 누군가 넣어준다는 개념의 제어의 역전
//    private PostRepository postRepository = new PostRepository();


    //기능을 수행하기 위해서는 PostRepository 에게 의존한다.
    // 주입을 받고싶은 객체는 final 붙이셍
    // 자바 빈 등록 - 객체 주입을 위해 의존객체 등록을 맡긴다.
    private final PostRepository postRepository;

    /**
     *
     * 의존성 주입 종류
     * 생성자 주입, 세터주입, 필드 주입
     * 세터 주입은 쓰면 안된다. = 이유 : 세터는 프로그램 실행중에도 원하면 아무때나 호출이 가능하다.
     * 그에 반해 생성자는 객체가 생성될때 단한번만 생성된다. 따라서 세터주입은 너무 불안정, 바뀔가능성있어서
     * 생성자 주입시에도  계속생성하면 불변성이 깨지지 않나? 싱글톤 패턴과 final 키워드를 통해 보호한다.
     * */

//
//    @Autowired  // 스프링 컨테이너 에게 의존 객체를 자동 주입해달라는 소리이다. 다만 생성자가 단하나일경우 생략가능
//    public PostApiController(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
    


    //게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> list(){
        log.info("/posts GET request");
        List<PostEntity> list = postRepository.findAll();
        return null;
    }
    //request param 과 동일 하게하면 pathVariable("") 임의 지정 불가
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable Long postNo){
        log.info("/posts/{} GET request");
        return null;
    }
    // 게시물 등록
    @PostMapping
    public ResponseEntity<?> create() {
        log.info("/posts POST request");
        return null;
    }

    // 게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modify(@PathVariable Long postNo) {
        log.info("/posts/{} PATCH request", postNo);
        return null;
    }
    // 게시물 삭제
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> remove(@PathVariable Long postNo) {
        log.info("/posts/{} DELETE request", postNo);
        return null;
    }





}
