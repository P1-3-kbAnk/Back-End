package com.kbank.backend.domain.address;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "si_tb")
public class Si {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "si_pk") // 마이바티스에선 이게 불필요?
    private long si_pk; // si_pk 이런식으로 정의 해야함 (마이바티스)

    @Column(name = "si_nm", unique = true, nullable = false)
    private String si_nm;

    @Builder
    public Si(String name) {
        this.si_nm = name;
    }

}
