package com.real_estate.demo.error;

public class NotFoundException extends BaseException{
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }
}
