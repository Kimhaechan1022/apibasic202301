package com.example.apibasic.validate;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class MemberInfoDto {

    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String userName;

    @JsonFormat(pattern = "yyMMdd")
    @Past
    private LocalDate birthOfDate;

    @Valid
    private Address address; //주소 정보
    @Valid
    private Card card; // 결제 카드 정보
}
