package com.chengfeng.study.myspringbootproject.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PermissionsController class
 * 权限测试
 * @author chengfeng
 * @date 2021/7/25 /0025 15:36
 */
@Controller
public class PermissionsController {

    @RequestMapping("/level1/{id}")
    public String toLevel1(@PathVariable("id") String id) {
        return "views/level" + id + "/level" + id;
    }
    @RequestMapping("/level2/{id}")
    public String toLevel2(@PathVariable("id") String id) {
        return "views/level" + id + "/level" + id;
    }
    @RequestMapping("/level3/{id}")
    public String toLevel3(@PathVariable("id") String id) {
        return "views/level" + id + "/level" + id;
    }
}
