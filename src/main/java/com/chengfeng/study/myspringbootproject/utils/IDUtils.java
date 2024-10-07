package com.chengfeng.study.myspringbootproject.utils;

import java.util.UUID;

public class IDUtils {

    public IDUtils() {
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("\\-", "").toLowerCase();
    }

    public static String generateOCID() {
        return OCID.get().toString().toLowerCase();
    }

}
