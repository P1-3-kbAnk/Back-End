package com.kbank.backend.controller;

import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.JwtTokenDto;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.ChemistRequestDto;
import com.kbank.backend.dto.request.DoctorRequestDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.service.AuthService;
import com.kbank.backend.service.ChemistService;
import com.kbank.backend.service.DoctorService;
import com.kbank.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/register")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/user")
    public ResponseDto<JwtTokenDto> userRegister(@UserId Long userId, @RequestBody UserRequestDto userRequestDto, HttpServletRequest request, HttpServletResponse response) {
        return ResponseDto.ok(authService.createUser(userId, userRequestDto));
    }

    @PostMapping("/doctor")
    public ResponseDto<JwtTokenDto> doctorRegister(@UserId Long doctorId, @RequestBody DoctorRequestDto doctorRequestDto, HttpServletRequest request, HttpServletResponse response) {
        return ResponseDto.ok(authService.createDoctor(doctorId, doctorRequestDto));
    }

    @PostMapping("/chemist")
    public ResponseDto<JwtTokenDto> chemistRegister(@UserId Long chemistId, @RequestBody ChemistRequestDto chemistRequestDto, HttpServletRequest request, HttpServletResponse response) {
        return ResponseDto.ok(authService.createChemist(chemistId, chemistRequestDto));
    }

}
