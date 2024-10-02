package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.address.Dong;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="pharmacy_tb")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pharmacy_pk")
    private Long pharmacyPk;

    @Column(name = "pharmacy_nm", nullable = false)
    private String pharmacyNm;  // 약국 이름

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;  // 전화번호

    @Column(name = "fax_no")
    private String faxNo;  // 팩스번호

    @Column(name = "pharmacy_no")
    private Long pharmacyNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_dong_fk", nullable = false)
    private Dong pharmacyDong;  // 동 필드에 대한 FK

    public Pharmacy(String pharmacyNm, String phoneNo, String faxNo, Dong pharmacyDong, Long pharmacyNo) {
        this.pharmacyNo = pharmacyNo;
        this.pharmacyNm = pharmacyNm;
        this.phoneNo = phoneNo;
        this.faxNo = faxNo;
        this.pharmacyDong = pharmacyDong;
        this.createYmd = LocalDateTime.now();
    }
}
