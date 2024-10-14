package com.kbank.backend.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.kbank.backend.domain.*;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PayService {

    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final PharmacyBillRepository pharmacyBillRepository;

    @Transactional
    public Boolean payment(Long prescriptionId, Long deductedAmount) {

        // 처방전 ID로 처방전 찾기
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        User user=userRepository.findByUserPk(prescription.getPreUser().getUserPk())
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));

        // 프론트엔드에서 받은 차감 금액으로 계좌 업데이트
        Long newAccount = user.getAccount() - deductedAmount;

        // 계좌가 부족한 경우 예외 발생
        if (newAccount < 0) {
            throw new CommonException(ErrorCode.INSUFFICIENT_FUNDS); // 새로운 에러코드 정의 필요
        }

        // 사용자 계좌 업데이트
        user.updateAccount(newAccount); // dynamic update

        // 약국 영수증 생성
        PharmacyBill pharmacyBill = PharmacyBill.builder()
                .totalPrice(deductedAmount)  // 프론트에서 받은 금액을 totalPrice로 사용
                .pharmacyBillPrescription(prescription)
                .pharmacyBillPharmacy(prescription.getPreChemist().getChemistPharmacy())
                .build();

        pharmacyBillRepository.save(pharmacyBill);

        // Firebase 알림 전송
        String fcmToken = user.getFcmNo();  // 사용자 FCM 토큰
        String title = "결제가 완료되었습니다";
        String body = "총 " + deductedAmount + "원이 결제되었습니다.\n계좌 잔액: " + newAccount + "원";  // 남은 잔액을 엔터로 한 줄 내림

        boolean isNotificationSent = sendFcmMessage(fcmToken, title, body);


        // 알림 전송 실패 처리 (원하는 방식으로 처리 가능)
        if (!isNotificationSent) {
            System.out.println("알림 전송 실패");
        }

        return Boolean.TRUE;
    }

    @Transactional
    public Boolean doctorPayment(Long prescriptionId) {

        // 처방전 ID로 처방전 찾기
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        User user=userRepository.findByUserPk(prescription.getPreUser().getUserPk())
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));

        // 프론트엔드에서 받은 차감 금액으로 계좌 업데이트
        Long newAccount = user.getAccount() - 8600L;

        // 계좌가 부족한 경우 예외 발생
        if (newAccount < 0) {
            throw new CommonException(ErrorCode.INSUFFICIENT_FUNDS); // 새로운 에러코드 정의 필요
        }

        // 사용자 계좌 업데이트
        user.updateAccount(newAccount); // dynamic update


        // Firebase 알림 전송
        String fcmToken = user.getFcmNo();  // 사용자 FCM 토큰
        String title = "결제가 완료되었습니다";
        String body = "총 8600원이 결제되었습니다.\n계좌 잔액: " + newAccount + "원";  // 남은 잔액을 엔터로 한 줄 내림

        boolean isNotificationSent = sendFcmMessage(fcmToken, title, body);


        // 알림 전송 실패 처리 (원하는 방식으로 처리 가능)
        if (!isNotificationSent) {
            System.out.println("알림 전송 실패");
        }

        return Boolean.TRUE;
    }
    /**파이어베이스 통신**/
    private boolean sendFcmMessage(String fcmToken, String title, String body) {
        Message message = Message.builder()
                .setToken(fcmToken)
                .setNotification(Notification.builder()
                        .setTitle("방갑다: " + title)  // 알림 제목에 서비스명 포함
                        .setBody(body)  // 알림 내용 설정
                        .build())
                .putData("sender", "방갑다")  // 추가 데이터 (앱 내부에서 사용 가능)
                .build();


        // 제대로 된 토큰이 아니면 메세지 전송 실패할 수 있음
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("message success: " + response);
            return Boolean.TRUE;
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.out.println("message fail");
            return Boolean.FALSE;
        }
    }





}
