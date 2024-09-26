package com.kbank.backend.service;

import com.kbank.backend.domain.Bill;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.repository.BillRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class BillService {

    private final BillRepository billRepository;

    // Bill 생성
    public Bill createBill(Bill bill) {

        return billRepository.save(bill);
    }

    // 모든 Bill 조회
    public List<Bill> getAllBills() {

        return billRepository.findAll();
    }

    // Bill 조회 (ID로 조회)
    public Optional<Bill> getBillById(Long billPk) {
        return billRepository.findById(billPk);
    }

    // 특정 totalPrice로 Bill 조회
    public List<Bill> getBillsByTotalPrice(long totalPrice) {
        return billRepository.findByTotalPrice(totalPrice);
    }

    // 특정 날짜에 생성된 Bill 조회
    public List<Bill> getBillsByBillYmd(LocalDateTime billYmd) {
        return billRepository.findByBillYmd(billYmd);
    }

    // 처방전 ID로  Bill 조회
    public List<Bill> getBillsByPrescriptionFk(Prescription billPrescriptionFk) {
        return billRepository.findByBillPrescriptionFk(billPrescriptionFk);
    }


    // Bill 삭제
    public void deleteBill(Long billPk) {

        billRepository.deleteById(billPk);
    }
}
