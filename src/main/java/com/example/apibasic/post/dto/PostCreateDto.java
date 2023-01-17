package com.example.apibasic.post.dto;


import com.example.apibasic.post.entity.PostEntity;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.PrimitiveIterator;

// 데이터 검증은 dto에서만 수행한다 Entity에 해당 책임을 전가하지말자

@Setter @Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PostCreateDto {


    // 클라이언트 에서 검증 한다해도 브라우저 안통하고 공격하면 답이 없기에 여기서 막아야한다.
    // 클라이언트, 서버 밸리데이션, 디비에서 막기 삼단계로 막아야 한다.
    // 보통 클라이언트에게 요청을 받은  DTO를 검증을 수행한다

    /**
     * NotNull 널값일 경우 에러발생
     * NotEmpty 빈문자열일경우 에러발생
     * NotBlank null이거나 빈문자열일때 에러발생
     */

    @NotBlank
    private String writer;
    @NotBlank
    private String title;
    private String content;
//    private List<String> hashTags;

    // PostEntity로 변환하는 유틸 메소드
    public PostEntity toEntity(){
        return PostEntity.builder()
//                .postNo(PostEntity.sequence++)
                .writer(this.writer)
                .contents(this.content)
                .title(this.title)
//                .hashTags(this.hashTags)
                .createDate(LocalDateTime.now())
                .build();
    }

}
