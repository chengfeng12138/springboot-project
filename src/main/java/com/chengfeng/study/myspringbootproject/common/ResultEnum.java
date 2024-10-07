package com.chengfeng.study.myspringbootproject.common;

/**
 * ResultEnum class
 *
 * @author chengfeng
 * @date 2020/7/26 /0026 22:02
 */
public enum ResultEnum {
    //
    UNKNOWN_ERROR(-1,"未知错误"),
    //
    SUCCESS(200,"成功"),
    //
    USER_NOT_EXIST(1,"用户不存在"),
    //
    USER_IS_EXISTS(2,"用户已存在"),
    //
    DATA_IS_NULL(3,"数据为空"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
