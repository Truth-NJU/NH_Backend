package com.example.backendnh.vo.http;

import lombok.Getter;

@Getter
public enum ClassHttpStatus implements HttpStatus{

    ADD_CLASS_FAIL(4014, "新增分类失败");

    ClassHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;
}
