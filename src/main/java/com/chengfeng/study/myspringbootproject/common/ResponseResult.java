package com.chengfeng.study.myspringbootproject.common;

import java.util.Objects;

/**
 * ResponseResult class
 *
 * @author chengfeng
 * @date 2020/7/26 /0026 21:49
 */
public class ResponseResult {
    private Integer code;  //响应代码
    private String message; //响应信息
    private Object data; //响应数据
    private Boolean success;  //是否成功
    private String type; //响应类型

    public ResponseResult(Integer code, String message, Object data, Boolean success, String type) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
        this.type = type;
    }

    public ResponseResult(){}

    public ResponseResult(boolean flag) {
        this.success = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseResult that = (ResponseResult) o;
        return Objects.equals(code, that.code) && Objects.equals(message, that.message) && Objects.equals(data, that.data) && Objects.equals(success, that.success) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data, success, type);
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", success=" + success +
                ", type='" + type + '\'' +
                '}';
    }

}
