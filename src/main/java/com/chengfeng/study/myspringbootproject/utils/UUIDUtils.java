package com.chengfeng.study.myspringbootproject.utils;

import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtils {


    /**
     * 获取随机UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static void main(String[] args) {
        System.out.println(UUIDUtils.getUUID());
    }

}
