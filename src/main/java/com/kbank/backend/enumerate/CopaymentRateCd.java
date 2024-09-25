package com.kbank.backend.enumerate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
제목 : 본인부담률 enum
설명 : 주석 참고
담당자 : 김성헌
*/
@Getter
@RequiredArgsConstructor
public enum CopaymentRateCd {

    A("A","0.5"),
    B("B","0.8" ),
    C("C","0.3"),
    D("D","건강보험(의료급여) 1.0"),
    V("V","보훈 등 1.0"),
    W("W","보훈 비급여");

    private final String name;
    private final String example;

}
