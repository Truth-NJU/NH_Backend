package com.example.backendnh.vo.http;

import lombok.Getter;

@Getter
public enum OrgHttpStatus implements HttpStatus {
    ADD_ORG_FAIL(4015, "新增机构失败");

    OrgHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;
}
