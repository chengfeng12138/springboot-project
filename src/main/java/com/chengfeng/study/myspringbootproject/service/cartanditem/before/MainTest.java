package com.chengfeng.study.myspringbootproject.service.cartanditem.before;

import com.chengfeng.study.myspringbootproject.pojo.Cart;

import java.util.HashMap;
import java.util.Map;

/**
 * MainTest class
 *
 * @author chengfeng
 * @date 2022/4/16 /0016 18:14
 */
public class MainTest {
    public static void main(String[] args) {
        long userId = 1111111L;
        Map<Long, Integer> items = new HashMap<>();
        items.put(100L, 10);
        items.put(101L, 20);
        items.put(102L, 30);

        String userCategory = "Normal";
        Cart cart = extracted(items, userCategory, userId);
        System.out.println("普通用户购物车 = " + cart);

        String userCategory2 = "Vip";
        Cart cart2 = extracted(items, userCategory2, userId);
        System.out.println("vip用户购物车 = " + cart2);

        String userCategory3 = "Internal";
        Cart cart3 = extracted(items, userCategory3, userId);
        System.out.println("内部用户购物车 = " + cart3);
    }

    private static Cart extracted(Map<Long, Integer> items, String userCategory, long userId) {
        //普通用户处理逻辑
        if ("Normal".equals(userCategory)) {
            NormalUserCart normalUserCart = new NormalUserCart();
            return normalUserCart.process(userId, items);
        }
        //VIP用户处理逻辑
        if ("Vip".equals(userCategory)) {
            VipUserCart vipUserCart = new VipUserCart();
            return vipUserCart.process(userId, items);
        }
        //内部用户处理逻辑
        if ("Internal".equals(userCategory)) {
            InternalUserCart internalUserCart = new InternalUserCart();
            return internalUserCart.process(userId, items);
        }
        return new Cart();
    }
}
