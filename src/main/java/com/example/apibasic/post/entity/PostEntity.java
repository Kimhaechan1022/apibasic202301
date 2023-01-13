package com.example.apibasic.post.entity;

// 게시물의 데이터 java beans

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostEntity {
    private Long postNo; //게시물 식별번호
    private String writer; //작성자
    private String title; // 게시물 제목

    private String contents; //내영

    private List<String> hashTags; //해시태그 목록

    private LocalDateTime createDate; //작성시간
    private LocalDateTime modifyDate; //수정시간

}
