package com.kbank.backend.service;

import com.kbank.backend.domain.Bill;
import com.kbank.backend.domain.Hospital;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

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

    // 특정 Prescription에 대한 Bill 조회
    public List<Bill> getBillsByPrescriptionFk(Prescription billPrescriptionFk) {
        return billRepository.findByBillPrescriptionFk(billPrescriptionFk);
    }

    // Bill 업데이트
    public Bill updateBill(Long billPk, Bill updatedBill) {
        return billRepository.findById(billPk)
                .map(existingBill -> {
                    existingBill.setTotalPrice(updatedBill.getTotalPrice());
                    existingBill.setBillYmd(updatedBill.getBillYmd());
                    existingBill.setBillPrescriptionFk(updatedBill.getBillPrescriptionFk());
                    return billRepository.save(existingBill);
                }).orElseThrow(() -> new IllegalArgumentException("Bill not found with id: " + billPk));
    }

    // Bill 삭제
    public void deleteBill(Long billPk) {

        billRepository.deleteById(billPk);
    }
}
