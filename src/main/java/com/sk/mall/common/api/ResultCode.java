package com.sk.mall.common.api;

/**
 * Created by SongKun on 2020/2/7 4:22 下午
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_fAILED(400,"参数检验失败"),
    UNAUTHORIZED(401,"暂未登录或token已过期"),
    FORBIDDEN(403,"没有相关权限");
    private long code;
    private String message;
    private ResultCode(long code,String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
