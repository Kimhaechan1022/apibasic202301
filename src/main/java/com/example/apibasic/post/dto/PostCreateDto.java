package com.example.apibasic.post.dto;


import com.example.apibasic.post.entity.PostEntity;
import lombok.*;

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
    private String writer;
    private String title;
    private String content;
    private List<String> hashTags;

    // PostEntity로 변환하는 유틸 메소드
    public PostEntity toEntity(){
        return PostEntity.builder()
                .postNo(PostEntity.sequence++)
                .writer(this.writer)
                .contents(this.content)
                .title(this.title)
                .hashTags(this.hashTags)
                .createDate(LocalDateTime.now())
                .build();
    }

}
