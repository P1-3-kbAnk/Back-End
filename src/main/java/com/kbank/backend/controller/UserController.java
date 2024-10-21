package com.kbank.backend.controller;

import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.dto.response.UserResponseDto;
import com.kbank.backend.service.FcmService;
import com.kbank.backend.service.UserService;
import com.kbank.backend.service.prescription.UserPrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class UserController {

    private final UserPrescriptionService userPrescriptionService;
    private final UserService userService;
    private final FcmService fcmService;

    @PostMapping("/notification/test")
    public String sendTestNotification(@UserId Long userId) {
        boolean isSent = fcmService.sendTestNotification(userId);

        if (isSent) {
            return "테스트 알림이 성공적으로 전송되었습니다.";
        } else {
            return "테스트 알림 전송에 실패하였습니다.";
        }
    }

    @GetMapping("/name")
    public ResponseDto<?> getUserName(@UserId Long userId) {
        System.out.println("****************************");
        return ResponseDto.ok(userService.getUserName(userId));
    }

//
//    // 수정요함
//    //사용자 계좌정보 조회
//    @GetMapping("/account")
//    public ResponseDto<UserResponseDto> detailAccount(@RequestParam(name = "userId") Long userId){
//        return ResponseDto.ok(userService.getUserById(userId));
//    }


    @PatchMapping("/modify/medicineTime")
    public ResponseDto<Boolean> updateAlarm(@UserId Long userId,
                                            @RequestBody UserRequestDto userRequestDto) {
        return ResponseDto.created(userService.updateAlarm(userId,userRequestDto));
    }

    //사용자 계좌정보 조회
    @GetMapping("/account")
    public ResponseDto<UserResponseDto> detailAccount(@UserId Long userId) {
        return ResponseDto.ok(userService.getUserById(userId));
    }

    //사용자 계좌 정보 수정
    @PatchMapping("/modify/account")
    public ResponseDto<?> modifyAccount(@UserId Long userId,
                                        @RequestBody UserRequestDto userRequestDto){
        return ResponseDto.ok(userService.updateAccountInfo(userId,userRequestDto));
    }

    @PatchMapping("/prescription/{id}")
    public ResponseDto<Boolean> updatePrescriptionSt(@PathVariable("id") Long prescriptionId) {
        return ResponseDto.ok(userPrescriptionService.setPrescriptionSt(prescriptionId));
    }

}
