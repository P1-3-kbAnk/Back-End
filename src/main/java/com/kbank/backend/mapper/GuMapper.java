package com.kbank.backend.mapper;


import com.kbank.backend.domain.address.Dong;
import com.kbank.backend.domain.address.Gu;
import com.kbank.backend.domain.address.Si;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface GuMapper {

    @Transactional
    List<Gu> findAll();

    @Transactional
    Gu findById(long id);

    @Transactional
    Gu findByName(String name);

    @Transactional
    Si findSiById(long id);

    @Transactional
    Si findSiByName(String name);

    @Transactional
    List<Dong> findAllDongByGu(long id);

    @Transactional
    List<Dong> findAllDongByGuName(String name);

}
