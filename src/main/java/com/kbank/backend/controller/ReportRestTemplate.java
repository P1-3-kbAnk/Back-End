package com.kbank.backend.controller;

import com.kbank.backend.dto.request.ReportRequestDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


// Fast API로 요청을 보내는 코드

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
@Slf4j
public class ReportRestTemplate {

    private final RestTemplate restTemplate;

    public ReportRequestDto getReport(@Valid Map<String, String> q) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // JSON 객체로 데이터 감싸기
        Map<String, String> text = new HashMap<>();

        text.put("medi", q.get("medi"));
        text.put("code", q.get("code"));

        HttpEntity<Map<String, String>> request = new HttpEntity<>(text, headers);
        HttpEntity<ReportRequestDto> res;

        try {
            String baseUrl = "http://43.202.43.253:8000/";
            res = restTemplate.exchange(
                    baseUrl + "/report",
                    HttpMethod.POST,
                    request,  // JSON 데이터를 감싸는 HttpEntity 사용
                    ReportRequestDto.class
            );

            //System.out.println(res.getBody());
        } catch (HttpClientErrorException e) {
            // 클라이언트 쪽에서 발생한 오류 처리
            System.out.println("Client Error: " + e.getResponseBodyAsString());
            throw new CommonException(ErrorCode.SERVER_ERROR);
        } catch (RestClientException e) {
            // 서버 응답이 4xx 또는 5xx일 때 발생하는 오류
            System.out.println("RestClientException: " + e.getMessage());
            throw new CommonException(ErrorCode.SERVER_ERROR);
        }
        return (res.getBody());
    }


}
