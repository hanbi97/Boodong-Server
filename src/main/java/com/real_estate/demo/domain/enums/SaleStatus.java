package com.real_estate.demo.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SaleStatus {
    SALE("SALE","판매중"),
    SOLDOUT("SOLDOUT","판매완료"),
    HOLDING("HOLDING","보류중");

    private final String key;
    private final String value;
}
