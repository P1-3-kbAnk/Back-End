package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.dto.response.UserResponseDto;
import com.kbank.backend.service.PrescriptionService;
import com.kbank.backend.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class UserController {

    private final PrescriptionService prescriptionService;
    private final UserService userService;
//
//    @PostMapping("/register")
//    public ResponseDto<Boolean> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
//        return ResponseDto.created(userService.createUser(userRequestDto));
//    }
//
//    // 수정요함
//    //사용자 계좌정보 조회
//    @GetMapping("/account")
//    public ResponseDto<UserResponseDto> detailAccount(@RequestParam(name = "userId") Long userId){
//        return ResponseDto.ok(userService.getUserById(userId));
//    }

    @GetMapping("/prescription/list")
    public ResponseDto<?> getAllPrescriptionList(@RequestParam(name = "userId") Long userId,
                                                 @RequestParam(name = "pageIndex") @Valid @NotNull @Min(0) Integer pageIndex,
                                                 @RequestParam(name = "pageSize") @Valid @NotNull @Min(1) Integer pageSize) {
        return ResponseDto.ok(prescriptionService.getAllPrescriptionList(userId, pageIndex, pageSize));
    }

    @PatchMapping("/modify/medicineTime")
    public ResponseDto<Boolean> updateAlarm(@RequestParam(name = "userId") Long userId,
                                           @RequestBody UserRequestDto userRequestDto){
        return ResponseDto.created(userService.updateAlarm(userId,userRequestDto));
    }

    //사용자 계좌정보 조회
    @GetMapping("/account")
    public ResponseDto<UserResponseDto> detailAccount(@RequestParam(name = "userId") Long userId){
        return ResponseDto.ok(userService.getUserById(userId));
    }
    //사용자 계좌 정보 수정
    @PatchMapping("/modify/account")
    public ResponseDto<?> modifyAccount(@RequestParam(name = "userId") Long userId,
                                        @RequestBody UserRequestDto userRequestDto){
        return ResponseDto.ok(userService.updateAccountInfo(userId,userRequestDto));
    }

}
