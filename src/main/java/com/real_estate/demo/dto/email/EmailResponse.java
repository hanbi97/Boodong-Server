package com.real_estate.demo.dto.email;

import com.real_estate.demo.dto.BaseResponse;
import lombok.Builder;

public class EmailResponse extends BaseResponse {
    String email;

    @Builder
    public EmailResponse(boolean success, String message, String email) {
        super(success, message);
        this.email = email;
    }
}
