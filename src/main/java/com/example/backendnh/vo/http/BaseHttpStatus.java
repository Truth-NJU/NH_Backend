package com.example.backendnh.vo.http;

import lombok.Getter;

/**
 * @author taozehua
 * @since 2022-11-25
 */
@Getter
public enum BaseHttpStatus implements HttpStatus {
    COMMON_OK(4000, "ok");

    BaseHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;
}