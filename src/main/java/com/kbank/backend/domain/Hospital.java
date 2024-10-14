package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.address.Dong;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="hospital_tb")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hospital_pk")
    private Long hospitalPk;

    @Column(name="hospital_nm", nullable=false)
    private String hospitalNm;

    @Column(name="phone_no", nullable=false)
    private String phoneNo;

    @Column(name = "hospital_no", nullable=false, unique = true)
    private Long hospitalNo;

    @Column(name = "fax_no")
    private String faxNo;

    @Column(name = "detail_address")
    private String detailAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hospital_dong_fk")
    private Dong hospitalDong;

    @Builder
    public Hospital(String hospitalNm, String phoneNo, Long hospitalNo, String faxNo, Dong hospitalDong, String detailAddress) {
        this.hospitalNm = hospitalNm;
        this.phoneNo = phoneNo;
        this.hospitalNo = hospitalNo;
        this.detailAddress = detailAddress;
        this.faxNo = faxNo;
        this.hospitalDong = hospitalDong;
        this.createYmd = LocalDateTime.now();
    }

    @PrePersist
    public void generateUniqueRandomValue() {
        UUID uuid = UUID.randomUUID();
        this.hospitalNo = Math.abs(uuid.getMostSignificantBits());
    }

}
