package com.chengfeng.study.myspringbootproject.service.cartanditem;

import java.math.BigDecimal;

/**
 * Common class
 *
 * @author chengfeng
 * @date 2022/4/16 /0016 16:13
 */
public class Common {
    public static BigDecimal getItemPrice(Long key) {
        return BigDecimal.valueOf(key + 100);
    }
}
