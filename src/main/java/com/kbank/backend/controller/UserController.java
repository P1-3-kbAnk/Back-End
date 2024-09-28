package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseDto<Boolean> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseDto.created(userService.createUser(userRequestDto));
    }
}
