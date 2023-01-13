package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostModReqDto {

    private Long postNo;
    private String title;
    private String content;

    public PostEntity toEntity(){
        return PostEntity.builder()
                .contents(this.content)
                .title(this.title)
                .build();
    }

}
