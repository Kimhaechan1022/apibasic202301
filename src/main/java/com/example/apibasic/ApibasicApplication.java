package com.example.apibasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스레드 풀애서 재활용하는 방식으로 한정된 멀티스레드를 운용하므니당

@SpringBootApplication
public class ApibasicApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApibasicApplication.class, args);
	}

}
