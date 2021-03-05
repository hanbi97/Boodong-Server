package com.real_estate.demo.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER(400, null,"invalid request data"),
    NOT_FOUND(404, "C002","request data not found");
    private final int status;
    private final String code;
    private final String message;
}
