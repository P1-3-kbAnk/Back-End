package com.kbank.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageInfo {
    private Integer currentPage; // 현재 페이지 번호
    private Integer totalPages; // 전체 페이지 수
    private Integer pageSize; // 페이지 사이즈
    private Long totalItems; // 전체 데이터 수
    private Integer currentItems; // 현재 데이터 수
}