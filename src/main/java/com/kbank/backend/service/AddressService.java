package com.kbank.backend.service;

import com.kbank.backend.domain.address.*;
import com.kbank.backend.mapper.DongMapper;
import com.kbank.backend.mapper.GuMapper;
import com.kbank.backend.mapper.SiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final SiMapper siMapper;
    private final GuMapper guMapper;
    private final DongMapper dongMapper;

    public List<Si> getAllSi() {
        return siMapper.findAll();
    }

    public List<Gu> getAllGu() {
        return guMapper.findAll();
    }

    public List<Dong> getAllDong() {
        return dongMapper.findAll();
    }


    public List<Gu> getAllGuBySiId(Long id) {
        return siMapper.findAllGuBySi(id);
    }

    public List<Gu> getAllGuBySiName(String siName) {
        return siMapper.findAllGuBySiName(siName);
    }

    public Si getSiBySiId(Long id) {
        return siMapper.findById(id);
    }

    public Si getSiBySiName(String siName) {
        return siMapper.findByName(siName);
    }

    public Gu getGuByGuId(Long id) {
        return guMapper.findById(id);
    }

    public Gu getGuByGuName(String guName) {
        return guMapper.findByName(guName);
    }

    public List<Dong> getAllDongByGuId(Long id) {
        return guMapper.findAllDongByGu(id);
    }

    public List<Dong> getAllDongByGuName(String guName) {
        return guMapper.findAllDongByGuName(guName);
    }

    public Dong getDongByDongId(Long id) {
        return dongMapper.findById(id);
    }

    public Dong getDongByDongName(String name) {
        return dongMapper.findByName(name);
    }

    public Si getSiByDongName(String name) {
        return dongMapper.findSiByName(name);
    }

    public Si getSiByDongId(Long id) {
        return dongMapper.findSiById(id);
    }

    public Gu getGuByDongName(String name) {
        return dongMapper.findGuByName(name);
    }

    public Gu getGuByDongId(Long id) {
        return dongMapper.findGuById(id);
    }

}
