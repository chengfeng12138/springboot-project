package com.chengfeng.study.myspringbootproject;

import cn.hutool.core.map.multi.ListValueMap;
import cn.hutool.http.HttpUtil;

/**
 * MainTest class
 *
 * @author chengfeng
 * @date 2021/7/30 /0030 23:02
 */
public class mainTest {
    public static void main(String[] args) {
        HttpUtil.createServer(8888)
                .addAction("/", (req, res) -> {
                    ListValueMap<String, String> params = req.getParams();
                    System.out.println("params = " + params);
                    res.setContentLength("Hello Hutool Server".length());
                    res.write("Hello Hutool Server");
                })
                .start();
    }
}
