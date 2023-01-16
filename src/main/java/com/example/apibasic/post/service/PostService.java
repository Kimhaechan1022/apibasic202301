package com.example.apibasic.post.service;

import com.example.apibasic.post.dto.PostCreateDto;
import com.example.apibasic.post.dto.PostModReqDto;
import com.example.apibasic.post.dto.PostResponseDto;
import com.example.apibasic.post.dto.PostUnitResponseDto;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    // 목록조회 중간처리
    public List<PostResponseDto> getList(){
        List<PostEntity> list = postRepository.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("조회결과 X");
        }

        //entity list를 dto 리스트로 변환해서 클라이언트에 응답해야한다이잉
        List<PostResponseDto> responseDtoList = list.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
        return responseDtoList;
    }


    // 개별 조회
    public PostUnitResponseDto getDetail(Long postNo) {
        PostEntity post = postRepository.findOne(postNo);
        return new PostUnitResponseDto(post);
    }

    public boolean create(PostCreateDto createDto) {
        postRepository.save(createDto.toEntity());
        boolean flag = true;
        return flag;
    }

    public boolean update(PostModReqDto modReqDto) {
        PostEntity targetModEntity = postRepository.findOne(modReqDto.getPostNo());

        // null check 해야함 (하나만 바꾸고 싶을떄를 고려해야함)
        if (modReqDto.getContent() != null) {
            targetModEntity.setContents(modReqDto.getContent());
        }
        if (modReqDto.getTitle() != null) {
            targetModEntity.setTitle(modReqDto.getTitle());
        }

        targetModEntity.setModifyDate(LocalDateTime.now());
        postRepository.save(targetModEntity);

        boolean flag = true;
        return flag;
    }

    public boolean delete(Long postNo) {
        postRepository.delete(postNo);
        boolean flag = true;
        return flag;
    }


}