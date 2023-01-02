package com.example.backendnh.vo.http;

import lombok.Getter;

@Getter
public enum ArchHttpStatus implements HttpStatus{

    ADD_ARCH_FAIL(4011, "新增档案失败"),

    ADD_ARCH_REMARK_FAIL(4012, "提交反馈失败"),

    DEL_ARCH_REMARK_FAIL(4013, "删除反馈失败"),

    UPLOAD_FILE_FAIL(4016, "文件上传失败"),

    UPDATE_ARCH_FAIL(4017, "档案更新失败");

    ArchHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;
}
