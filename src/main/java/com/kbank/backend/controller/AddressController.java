package com.kbank.backend.controller;


import com.kbank.backend.domain.address.Dong;
import com.kbank.backend.domain.address.Gu;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/get/gu/{name}")
    public ResponseDto<List<Gu>> getGuBySi(@PathVariable("name") String name) {
        return ResponseDto.ok(addressService.getAllGuBySiName((name)));
    }

    @GetMapping("/get/dong/{name}")
    public ResponseDto<List<Dong>> getDongByGu(@PathVariable("name") String name) {
        return ResponseDto.ok(addressService.getAllDongByGuName(name));
    }
}
