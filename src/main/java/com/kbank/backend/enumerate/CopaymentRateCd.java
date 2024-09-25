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

    A("A","100분의 50 본인부담"),
    B("B","100분의 80본인부담" ),
    C("C","100분의 30본인부담"),
    D("D","건강보험(의료급여) 100분의 100본인부담"),
    V("V","보훈 등 100분의 100부담"),
    W("W","보훈 비급여");

    private final String name;
    private final String example;

}
