package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Hospital;
import com.kbank.backend.domain.address.Dong;
import lombok.*;

import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalResponse {

    private long hospitalPk;
    private Dong hospitalDongFk;

    private String hospitalNm;
    private String phoneNo;
    private long hospitalNo;
    private String faxNo;

    @Builder
    public HospitalResponse(Dong hospitalDongFk, String hospitalNm, String phoneNo, long hospitalNo, String faxNo) {
        this.hospitalDongFk = hospitalDongFk;
        this.hospitalNm = hospitalNm;
        this.phoneNo = phoneNo;
        this.hospitalNo = hospitalNo;
        this.faxNo = faxNo;
    }


    public HospitalResponse(Optional<Hospital> hospital) {
    }

    public HospitalResponse(Hospital hospital) {
    }
}
