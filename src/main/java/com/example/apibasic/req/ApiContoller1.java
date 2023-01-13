package com.example.apibasic.req;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.net.http.HttpHeaders;
import java.util.Enumeration;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller // 클라이언트가 보낸 요청을 받을 수 있다리
@Slf4j // 로그 라이브러리
@ResponseBody    @RequestMapping("/say")
public class ApiContoller1 {

    // 요청 처리 메소드
    // request 매핑을 그냥 쓰면 allow 제한 없이 모든 메소드를 수용하게 된다.
    @RequestMapping(value = "/say/hello", method = {GET, POST})
    public String hello(HttpServletRequest request) {

        // System 접근하면 안되는 이유 native 하드웨어 언어(C와 같은)를 사용하기 때문에 리소스 낭비 심함 추가적 파일 출력 소스코드도 필요함
        // 어쨋든 리소스도 많이 낭비하고 실행될 테스크가 복잡하다잉
        log.info("/hello spring - {}", request.getMethod());
        log.trace("trace");
        log.debug("debug");
        log.warn("warn");
        log.error("errrrr");
        return " ";
    }

    @GetMapping("/greet")
    public String greet(){
        log.info("/say/greet GET 요청");
        return "";
    }
    @PostMapping("/greet")
    public String greet2(){
        log.info("/say/greet Post 요청");
        return "";
    }

    // 요청 헤더정보 얻기
    @GetMapping("/header")
    public String header(HttpServletRequest request){
        log.info("==========header=======");
        String host = request.getHeader("HOST");
        String acc = request.getHeader("Accept");
        String pet = request.getHeader("pet");

        log.info("# host : " + host);
        log.info("# acc : " + acc);
        log.info("# pet : " + pet);
        return " ";
    }

}
