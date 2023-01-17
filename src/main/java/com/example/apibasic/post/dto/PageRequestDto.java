package com.example.apibasic.post.dto;


//클라이언트가 보낸 페이지 정보를 가진 객체


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class PageRequestDto {
    private int page; // 요청한 페이지 번호
    private int sizePerPage; // 한페이지에 보여줄 데이터 수
    private PageResponseDto pageResponseDto;

    public PageRequestDto(PageResponseDto pageResponseDto) {
        this.pageResponseDto = pageResponseDto;
    }

    // 기본성생자, 기본값 원래였음 0
    // 초기 여청시에 사용할 데이터
    public PageRequestDto() {
        this.page = 1;
        this.sizePerPage = 10;
    }

    public void setPage(int page) {
        // 잘못된 값 방지
        if (page < 1 || page > Integer.MAX_VALUE) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setSizePerPage(int sizePerPage) {
        if(sizePerPage <10 || sizePerPage>100){
            this.sizePerPage = 10;
            return;
        }
        this.sizePerPage = sizePerPage;
    }
}
