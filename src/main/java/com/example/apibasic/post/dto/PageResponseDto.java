package com.example.apibasic.post.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

// 클라이언트에게 응답할 페이지 정보
/*
    pageInfo : {
        "startPage" : 1,
        "endPage" : 10,
        "currentPage" : 3,
        "prev" : false,
        "next" : true,
        "totalCount": 500
    }
 */

@ToString @Setter @Getter

public class PageResponseDto<T> {

    private int startPage;
    private int endPage;
    private int currentPage;


    private boolean prev;
    private boolean next;
    private int totalCount;

    // 페이지 개스
    private static final int PAGE_COUNT = 10;

    public PageResponseDto(Page<T> pageData) {
        this.currentPage = pageData.getPageable().getPageNumber() + 1;
        this.totalCount = (int) pageData.getTotalElements();

        // int /int = 몫만 나옴
        this.endPage = (int) (Math.ceil((double) currentPage / PAGE_COUNT) * PAGE_COUNT);
        this.startPage = endPage - PAGE_COUNT + 1;
        this.prev = startPage > 1;

        // 페이지 마지막 구간에서 엔드페이지값 보정이되어야함
        // 실제 끝 페이지를 구함
//        int realEnd = (int) Math.ceil(totalCount / pageData.getSize());
        int realEnd = pageData.getTotalPages();

        // 언제 보정해야함?> 엔드페이지보다 리얼엔드가 작을때 (마지막구간)
        if(realEnd < endPage){
            this.endPage = realEnd;
        }

        this.next = endPage < realEnd;
    }
}
