package com.example.backendnh.vo.http;

import lombok.Getter;

@Getter
public enum UserHttpStatus implements HttpStatus {

    USER_TOKEN_ERROR(4006, "未登录"),

    USER_REPEAT(4001, "邮箱已存在"),

    USER_NOT_EXIST(4002, "用户不存在"),

    USER_AlREADY_EXIST(4008, "用户已存在"),

    USER_DELETE_ERROR(4009, "用户删除失败"),

    PASSWD_ERROR(4003, "密码错误"),

    DATA_ERROR(4004, "数据格式错误"),

    CODE_ERROR(4005, "激活码错误"),

    USER_PRIVILEGE_ERROR(4007, "无操作权限"),

    OLD_PASSWD_ERROR(4010, "原密码错误");

    UserHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;
}