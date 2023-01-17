package com.example.apibasic.post.api;


import com.example.apibasic.post.dto.PostCreateDto;
import com.example.apibasic.post.dto.PostModReqDto;
import com.example.apibasic.post.dto.PostResponseDto;
import com.example.apibasic.post.service.PostService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// DTO에 대한 null check를 수시로 수행해야한다. 원하는 비지니스로직과 달라질수 있을테니!

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

    private final PostService postService;

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

    //payload  등록시 클라이언트가 보내는 정보


    //게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> list() {
        log.info("/posts GET request");

        try {
            return ResponseEntity.ok().body(postService.getList());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    //request param 과 동일 하게하면 pathVariable("") 임의 지정 불가
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable Long postNo){
        log.info("/posts/{} GET request");
        return ResponseEntity.ok().body(postService.getDetail(postNo));
        // body에 데이터를 못 담으면 .build 이용
    }
    // 게시물 등록

    //@Validated 를 붙여야 실제 검증 수행함
    @Parameters({
            @Parameter(name = "작성자", description = "게시물 작성자를 입력", example = "김철수")
    })
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody PostCreateDto createDto, BindingResult result) {
        log.info("/posts POST request");
        log.info("게시물 정보 : {}", createDto);
        if (createDto == null) {
            return ResponseEntity
                    .badRequest()
                    .body("게시물 정보를 전달해주세요.");
        }
//
//        if (result.hasErrors()) { // 검증에러가 발생할 시 true 리턴
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            fieldErrors.forEach(err -> {
//                log.warn("invalidated client data - {}", err.toString());
//            });
//            return ResponseEntity
//                    .badRequest()
//                    .body(fieldErrors);
//        }

        try {
            PostResponseDto postResponseDto = postService.insert(createDto);
            return ResponseEntity.ok().body(postResponseDto);
        }catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> modify(@RequestBody PostModReqDto modReqDto) {
        log.info("/posts/{} PATCH request", modReqDto);
        return postService.update(modReqDto)
                ?
                ResponseEntity.ok().body("MODIFY-SUCCESS")
                :ResponseEntity.badRequest().body("MODIFY-FAIL");
    }
    // 게시물 삭제
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> remove(@PathVariable Long postNo) {
        log.info("/posts/{} DELETE request", postNo);

        return postService.delete(postNo)
                ?
                ResponseEntity.ok().body("DELETE-SUCCESS")
                :ResponseEntity.badRequest().body("DELETE-FAIL");

    }

}
