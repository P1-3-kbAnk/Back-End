package com.kbank.backend.controller;


import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.service.FcmService;
import com.kbank.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fcm")
@CrossOrigin(origins = "*")
public class FcmController {
//    private final FcmService fcmService;
//    private final UserService userService;
//
//
//    @PatchMapping("/save/token")
//    public ResponseDto<Boolean> updateFcmToKen(@RequestParam(name = "userId") Long userId,
//                                               @RequestBody UserRequestDto userRequestDto){
//       return ResponseDto.created(userService.saveFcmToken(userId,userRequestDto));
//    }
//
//    // 테스트 알림 전송
//    @PostMapping("/test/send/{userId}")
//    public ResponseEntity<String> sendTestNotification(@PathVariable(name = "userId") Long userId) throws IOException {
//        boolean notificationSent = fcmService.sendTestNotification(userId);
//        if (notificationSent) {
//            return ResponseEntity.ok("success");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
//        }
//    }


}
