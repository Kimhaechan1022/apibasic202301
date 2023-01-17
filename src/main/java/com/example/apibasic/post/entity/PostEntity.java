package com.example.apibasic.post.entity;

// 게시물의 데이터 java beans

import com.example.apibasic.post.dto.PostResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tbl_post")
public class PostEntity {
//    public static Long sequence = 1L; // 연속된 일련변호


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private Long postNo; //게시물 식별번호

    @Column(nullable = false)
    private String writer; //작성자
    @Column(nullable = false)
    private String title; // 게시물 제목

    private String contents; //내영

//    private List<String> hashTags; //해시태그 목록

    @CreationTimestamp
    private LocalDateTime createDate;
    @UpdateTimestamp//작성시간
    private LocalDateTime modifyDate; //수정시간



}
