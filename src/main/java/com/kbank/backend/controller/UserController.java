package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.dto.response.UserResponseDto;
import com.kbank.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseDto<Boolean> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseDto.created(userService.createUser(userRequestDto));
    }

    //사용자 계좌정보 조회
    @GetMapping("/account")
    public ResponseDto<UserResponseDto> detailAccount(@RequestParam(name = "userId") Long userId){
        return ResponseDto.ok(userService.getUserById(userId));
    }
}
