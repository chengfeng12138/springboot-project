package com.chengfeng.study.myspringbootproject.pojo;

import lombok.Data;

/**
 * RequestInfo class
 *
 * @author chengfeng
 * @date 2022/8/14 /0014 17:11
 */
@Data
public class RequestInfo {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private Object result;
    private Long timeCost;
}
