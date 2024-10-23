package com.kbank.backend.controller;

import com.kbank.backend.annotation.Date;
import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.dto.response.MedicineResponseDto;
import com.kbank.backend.dto.response.UserResponseDto;
import com.kbank.backend.service.*;
import com.kbank.backend.service.prescription.UserPrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MedicineService medicineService;
    private final MedicineIntakeService medicineIntakeService;
    private final InjectionIntakeService injectionIntakeService;
    private final UserPrescriptionService userPrescriptionService;

    @GetMapping("/name")
    public ResponseDto<?> getUserName(@UserId Long userId) {
        return ResponseDto.ok(userService.getUserName(userId));
    }

    @PatchMapping("/alarm")
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
    @PatchMapping("/account")
    public ResponseDto<?> modifyAccount(@UserId Long userId,
                                        @RequestBody UserRequestDto userRequestDto){
        return ResponseDto.ok(userService.updateAccountInfo(userId,userRequestDto));
    }

    @PatchMapping("/prescriptions/{id}")
    public ResponseDto<Boolean> updatePrescriptionSt(@PathVariable("id") Long prescriptionId) {
        return ResponseDto.ok(userPrescriptionService.setPrescriptionSt(prescriptionId));
    }

    // 복용중인 약 상세 정보 조회
    @GetMapping("/medicines/{id}")
    public ResponseDto<MedicineResponseDto> medicineDetail(@PathVariable("id") Long medicineFk) {
        return ResponseDto.ok(medicineService.medicineDetail(medicineFk));
    }

    @GetMapping("/medicine-intakes")
    public ResponseDto<?> dateMedicineIntake(@UserId Long userId, @RequestParam("date") @Date String date) {
        return ResponseDto.ok(medicineIntakeService.getMedicineIntakeByDate(userId, LocalDate.parse(date)));
    }

    @PatchMapping("/medicine-intakes/{id}")
    public ResponseDto<Boolean> updateEatSt(@PathVariable("id") Long medInkPk) {
        return ResponseDto.ok(medicineIntakeService.updateEatSt(medInkPk));
    }

    @GetMapping("/injection-injects")
    public ResponseDto<?> getInjectionIntakesByDate(@UserId Long userId, @RequestParam("date") @Date String date) {
        return ResponseDto.ok(injectionIntakeService.getInjectionIntakeByDate(userId, LocalDate.parse(date)));
    }

    @PatchMapping("/injection-injects/{id}")
    public ResponseDto<Boolean> updateInjectionIntake(@PathVariable("id") Long id) {
        return ResponseDto.ok(injectionIntakeService.updateEatSt(id));
    }

}
