package com.ytempest.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ytempest.vo.BaseResult;

/**
 * @author ytempest
 * Description：
 */
public class ResultUtils {
    public static final int SUCCESS = 1;
    public static final int ERROR = -1;
    public static final Object NullObj = new NullJSON();
    public static final Object NullList = new NullList[]{};


    public static BaseResult result() {
        return new BaseResult();
    }

    public static BaseResult success() {
        return new BaseResult(1, "成功", new Object());
    }

    public static BaseResult error() {
        return new BaseResult(-1, "失败", new Object());
    }

    public static void setSuccess(BaseResult result, String msg, Object data) {
        set(result, SUCCESS, msg, data);
    }

    public static void setError(BaseResult result, String msg, Object data) {
        set(result, ERROR, msg, data);
    }

    public static void set(BaseResult result, int code, String msg, Object data) {
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
    }

    /**
     * 用于JSON返回空对象，即，data:{}
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class NullJSON {
        private String holder;
    }

    /**
     * 用于JSON返回空列表，即，data:[]
     */
    private interface NullList {
    }



}
