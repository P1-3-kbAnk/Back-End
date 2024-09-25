package com.kbank.backend.mapper;


import com.kbank.backend.domain.address.Gu;
import com.kbank.backend.domain.address.Si;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface SiMapper {

    @Transactional
    List<Si> findAll();

    @Transactional
    List<Gu> findAllGuBySi(Long id);

    @Transactional
    List<Gu> findAllGuBySiName(String name);

    @Transactional
    Si findByName(String name);

    @Transactional
    Si findById(Long id);


}
