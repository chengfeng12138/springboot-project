package com.chengfeng.study.myspringbootproject.utils;

import java.math.BigDecimal;


/**
 * 字符串工具类
 */
public class StringUtils {


    /**
     * 首字母转换为小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += 32;
            return new String(arr);
        }
        return str;
    }


    /**
     * 首字母转换为大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= 32;
            return new String(arr);
        }
        return str;
    }


    /**
     * 检查子符串出现过几次
     *
     * @param str    源字符串
     * @param subStr 子字符串
     * @return
     */
    public static int timesOf(String str, String subStr) {
        int foundCount = 0;
        if (subStr.equals("")) {
            return 0;
        }
        int fromIndex = str.indexOf(subStr);
        while (fromIndex != -1) {
            ++foundCount;
            fromIndex = str.indexOf(subStr, fromIndex + subStr.length());
        }
        return foundCount;
    }


    /**
     * 是否可转化为数字
     *
     * @param o
     * @return
     */
    public static boolean isNum(Object o) {
        try {
            new BigDecimal(o.toString());
            return true;
        } catch (Exception e) {
        }
        return false;
    }


    /**
     * 是否可转化为Long型数字
     *
     * @param o
     * @return
     */
    public static boolean isLong(Object o) {
        try {
            new Long(o.toString());
            return true;
        } catch (Exception e) {
        }
        return false;
    }


    /**
     * String转换为Integer
     */
    public static Integer stringToInt(String to) {
        return Integer.parseInt(to);
    }


    /**
     * String转换为Double
     */
    public static Double stringToDouble(String to) {
        return Double.parseDouble(to);
    }


    /**
     * String转换为Float
     */
    public static Float stringToFloat(String to) {
        return Float.parseFloat(to);
    }


    /**
     * String转换为Long
     */
    public static Long stringToLong(String to) {
        return Long.parseLong(to);
    }


    public static void main(String[] args) {
    }
}
