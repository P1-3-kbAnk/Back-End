package com.kbank.backend.domain.address.si;


/*
제목 : 주소 시 엔티티 정의
설명 : MyBatis는 JPA와는 다르게 필드명이 컬럼명과 무조건 같아야 한다는 유의점이 있다.
담당자 : 문환희
*/

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="si_tb")
public class Si {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="si_pk") // 마이바티스에선 이게 불필요
    private long si_pk; // si_pk 이런식으로 정의 해야함 (마이바티스)

    @Column(name="si_nm")
    private String si_nm;

}
