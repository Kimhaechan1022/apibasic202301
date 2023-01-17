package com.example.apibasic.post.repository;

import com.example.apibasic.post.dto.PageRequestDto;
import com.example.apibasic.post.dto.PostResponseDto;
import com.example.apibasic.post.entity.PostEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class PageTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void bulkInsert(){
        for (int i = 0; i < 500; i++) {
            PostEntity post = PostEntity.builder()
                    .title("안녕~~! "+i)
                    .writer("writer #" +i)
                    .contents("content" +i )
                    .build();
            postRepository.save(post);
        }
    }

    @Test
    void pagingTest(){
        PageRequestDto dto = PageRequestDto.builder()
                .page(4)
                .sizePerPage(10)
                .build();


        // 페이지 정보 생성
        PageRequest pageInfo = PageRequest.of(dto.getPage()-1, dto.getSizePerPage(),
                Sort.Direction.DESC, "createDate");

        // 페이징 처리는 페이지 정보를 생성하고 findall 에다가 넣어주면 댐
        Page<PostEntity> postEntities = postRepository.findAll(pageInfo);

        //실제 조회된 데이터
        List<PostEntity> content = postEntities.getContent();

        int totalPages = postEntities.getTotalPages();
        System.out.println("totalPages = " + totalPages);
        Long totalElement = postEntities.getTotalElements();
        System.out.println("totalElement = " + totalElement);

        
        System.out.println("postEntityList.size() = " + content.size());
        content.forEach(System.out::println);


    }

    @Test
    @DisplayName("제목에 3이 포함된 결과를 검색 후 1페이지 정보를 조회해아한다.")
    void pageTest(){
        String title = "3";
        PageRequest pageRequest = PageRequest.of(0,10,Sort.Direction.DESC,"createDate");
        Slice<PostEntity> postEntityPage = postRepository.findByTitleContaining(title, pageRequest);
        List<PostEntity> contents = postEntityPage.getContent();
        
        boolean next = postEntityPage.hasNext();
        boolean prev = postEntityPage.hasPrevious();
        System.out.println("next = " + next);
        System.out.println("prev = " + prev);

        contents.forEach(System.out::println);

    }
}
