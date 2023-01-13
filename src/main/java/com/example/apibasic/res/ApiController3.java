package com.example.apibasic.res;


import com.example.apibasic.req.ApiController2;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController //controller + ResponseBody(rendering demand on json, not html)
@Slf4j
public class ApiController3 {

    @GetMapping("/res1")
    public String hello() {
        return "hello";
    }

    // jackson 라이브러리가 자바의 객체와 데이터들을 json으로 만들어줌
    @GetMapping(value = "/res2", produces = "application/json") //produces = "application/json 기본값임
    public List<String> res2() {
        log.info("/get list");
        return List.of("짜장면", "볶음밥", "탕수육");
    }

    @GetMapping(value = "/res3", produces = "application/json") //produces = "application/json 기본값임
    public ApiController2.OrderInfo res3() {
        log.info("/get list");
        ApiController2.OrderInfo orderInfo = new ApiController2.OrderInfo();
        orderInfo.setOrderNo(12L);
        orderInfo.setGoodsAmount("2");
        orderInfo.setGoodsName("세탁기");

        return orderInfo;
    }

    // 사원 목록 정보
    @GetMapping("/employees")
    public List<Employee> empList() {
        return List.of(
                new Employee("홍길동", "영업부", LocalDate.of(2019, 12, 11))
                , new Employee("박영희", "구매부", LocalDate.of(2010, 3, 5))
                , new Employee("김수호", "개발부", LocalDate.of(2022, 4, 23))
        );
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Builder
    public static class Employee {
        private String empName;
        private String deptName;
        private LocalDate hireDate;
    }

    // 응답시에 응답 헤더 정보와 응답 상태 코드를 조작하려면 ResponseEntity 객체를 사용해야 한다잉
    // ResponseEntity 객체를 사용 하면 응답 코드를 조작가능
    @GetMapping("/res4")
    public ResponseEntity<?> res4(String nick){
        if (nick == null || nick.equals("")) {
            return ResponseEntity.badRequest().build();
        }
        Employee employee = Employee.builder()
                .empName(nick)
                .deptName("영업부")
                .hireDate(LocalDate.of(2019, 3, 31))
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("department", "business");
        httpHeaders.set("asdf", "qwerty");


        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .body(employee);

    }

}
