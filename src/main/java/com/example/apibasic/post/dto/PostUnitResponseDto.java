package com.example.apibasic.post.dto;


import com.example.apibasic.post.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

// 이렇게 하지말고 기존의것을 상속하자!!!!


@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostUnitResponseDto {
    private String author;
    private String title;
    private String content;

    private List<String> hashTags;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime regDate;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime modDate;

    public PostUnitResponseDto (PostEntity entity){
        this.author = entity.getWriter();
        this.content = entity.getContents();
        this.title = entity.getTitle();
        this.regDate = entity.getCreateDate();
        this.hashTags = entity.getHashTags();
        this.modDate = entity.getModifyDate();
    }


}
