package com.kbank.backend.mapper;


import com.kbank.backend.domain.address.Dong;
import com.kbank.backend.domain.address.Gu;
import com.kbank.backend.domain.address.Si;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface DongMapper {

    @Transactional
    List<Dong> findAll();

    @Transactional
    Dong findById(Long id);

    @Transactional
    Dong findByName(String name);

    @Transactional
    Si findSiById(long id);

    @Transactional
    Si findSiByName(String name);

    @Transactional
    Gu findGuById(long id);

    @Transactional
    Gu findGuByName(String name);

}
