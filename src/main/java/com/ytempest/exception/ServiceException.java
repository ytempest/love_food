package com.ytempest.exception;

public class ServiceException extends Exception {
    // 管理员用户不存在
    public static final int LOGIN_ADMIN_NOEXIT = 1;
    // 管理员密码错误
    public static final int LOGIN_ADMIN_PWD_ERR = 2;
    // API接口错误集合
    public static final int OTHRE = -1;

    // 页码数已经超出话题列表的结尾
    public static final int TOPIC_LIST_END = 3;

    // 页码数已经超出菜谱列表的结尾
    public static final int COOK_LIST_END = 4;

    // 页码数已经超出活动列表的结尾
    public static final int ACTIVITY_LIST_END = 5;

    // 页码数已经超出参与活动的菜谱列表的结尾
    public static final int PARTAKE_COOK_LIST_END = 6;

    // 页码数已经超出用户拥有的的菜谱列表的结尾
    public static final int USER_COOK_LIST_END = 7;

    // 页码数已经超出用户发布的话题列表的结尾
    public static final int USER_TOPIC_LIST_END = 8;

    // 页码数已经超出用户收藏的菜谱列表长度
    public static final int USER_COLLECT_LIST_END = 8;


    private int errorCode;

    public ServiceException(String message) {
        super(message);
        this.errorCode = OTHRE;
    }

    public ServiceException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
