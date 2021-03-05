package com.real_estate.demo.error;

import lombok.Getter;
import org.springframework.validation.Errors;

@Getter
public class InvalidParameterException extends BaseException {
    private final Errors errors;
    public InvalidParameterException(Errors errors) {
        super(ErrorCode.INVALID_PARAMETER);
        this.errors=errors;
    }
}
