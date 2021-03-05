package com.real_estate.demo.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    MONTHLY("MONTHLY", "월세"),
    JEONSE("JEONSE", "전세"),
    TRADE("TRADE","매매");

    private final String key;
    private final String value;
}
