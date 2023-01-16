package com.example.apibasic.jpabasic.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="userId")
@Entity
@Table(name = "tbl_member")
@Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //기본키 생성 정략
    @Column(name = "user_code")  //애트리뷰트 속성이름 부여
    private Long userId; //회원 식별 코드 (기본키)

    @Column(nullable = false, unique = true)  // NOT NULL 제약조건, 중복방지
    private String account;
    private String password;
    private String nickName;

    @Enumerated(EnumType.ORDINAL) // ORDINAL: 순번, STRING : 문자열
    private Gender gender;

    @CreationTimestamp  // Insert 시점의 서버시간을 자동으로 입력
    private LocalDateTime joinDate;

    @UpdateTimestamp // update 시점에 서버시간을 자동으로 입력
    private LocalDateTime modifyDate;

}
