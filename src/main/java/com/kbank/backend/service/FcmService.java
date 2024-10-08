package com.kbank.backend.service;

import com.google.firebase.messaging.*;
import com.kbank.backend.domain.User;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmService {

    private final UserRepository userRepository;

    /**시간 체크**/
    public Boolean sendNotification(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        LocalTime currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        Boolean timeCheck = false;

        if (currentTime.equals(user.getMorningAlarm())) {
            sendFcmMessage(user.getFcmNo(), "아침 약 알림", "아침 약 먹을 시간입니다.");
            timeCheck = true;
        }
        if (currentTime.equals(user.getLunchAlarm())) {
            sendFcmMessage(user.getFcmNo(), "점심 약 알림", "점심 약 먹을 시간입니다.");
            timeCheck = true;
        }
        if (currentTime.equals(user.getDinnerAlarm())) {
            sendFcmMessage(user.getFcmNo(), "저녁 약 알림", "저녁 약 먹을 시간입니다.");
            timeCheck = true;
        }
        return timeCheck;
    }
    /**파이어베이스 통신**/
    private boolean sendFcmMessage(String fcmToken,String title,String body){
        Message message = Message.builder()
                .setToken(fcmToken)
                .setNotification(Notification.builder()
                        .setTitle(title)  // 알림 제목 설정
                        .setBody(body)    // 알림 내용 설정
                        .build())
                .build();
        //제대로 된 토큰이 아니면 메세지 전송 실패할 수 있음
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
    //5분마다 알람체크
    //로그인 되어 있는 유저id 들어가야 하는데 일단 임시로 넣음
    @Scheduled(cron = "0 */5 * * * *")
    public void checkUSerAlarms(){
        System.out.println("shecedule run");
        Long userId=getLogInUserId();
        sendNotification(userId);
    }


    private Long getLogInUserId() {
        // Spring Security에서 id 가져와야 될듯요, 스케쥴러라 컨트롤러x 니깐?
        //일단 임시로 1넣음
        return 1L;
    }
//
//    // 테스트용 메서드: 현재 시간 상관없이 즉시 알림 전송
//    public boolean sendTestNotification(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
//        System.out.println("service_success");
//
//        // 즉시 알림 전송
//        return sendFcmMessage(user.getFcmNo(), "테스트 알림", "이것은 테스트용 알림입니다.");
//    }
}