package com.ytempest.exception;

public class ServiceException extends Exception {
    // 管理员用户不存在
    public static final int LOGIN_ADMIN_NOEXIT = 1;
    // 管理员密码错误
    public static final int LOGIN_ADMIN_PWD_ERR = 2;
    // API接口错误集合
    public static final int OTHER = -1;

    private int errorCode;

    public ServiceException(String message) {
        super(message);
        this.errorCode = OTHER;
    }

    public ServiceException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
