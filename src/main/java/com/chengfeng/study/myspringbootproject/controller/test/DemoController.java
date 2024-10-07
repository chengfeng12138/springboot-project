package com.chengfeng.study.myspringbootproject.controller.test;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.alibaba.fastjson.JSON;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.jparepository.AuthorRepository;
import com.chengfeng.study.myspringbootproject.pojo.User;
import com.chengfeng.study.myspringbootproject.utils.DateUtils;
import com.chengfeng.study.myspringbootproject.utils.Fields;
import com.chengfeng.study.myspringbootproject.utils.ResultUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DemoController class
 *
 * @author chengfeng
 * @date 2020/7/26 /0026 17:04
 */
@RestController
@Log4j2
public class DemoController {

    private AuthorRepository authorRepository;

    /**
     * @method set 注入
     * @author chengfeng
     * @date 2020/7/26 /0026 1:29
     */
    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/test")
    public String test() {
        int num = 1 / 0;
        return "Hello SpringBoot!";
    }

    @GetMapping("/stat")
    public Object druidStat() {
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

    @RequestMapping("findUser")
    public ResponseResult findUser(@RequestParam(value = "id", required = false) String id) {
        log.info("request time: [" + DateUtils.getNowTime_EN() + "]");
        if (id == null) return ResultUtil.error(Fields.HTTP_500, Fields.REQUEST_PARAM_ERROR);
        User user = new User();
        Optional<User> userOpt = authorRepository.findById(Integer.parseInt(id));
        if (!userOpt.isPresent()) return null;
        String result = JSON.toJSONString(userOpt.get());
        System.out.println(result);
        return ResultUtil.success(result);
    }

    @RequestMapping(value = "/postTest", method = RequestMethod.POST)
    public ResponseResult postTest(@RequestParam User user) {
        log.info(JSON.toJSONString(user));
        return ResultUtil.success("这是post请求...");
    }

    public static void main(String[] args) {
        String str = "1";
        List<User> users = new ArrayList<User>();
        StringBuilder result = new StringBuilder();
        result.append(str).append(",");
        System.out.println("result.toString() = " + result);
    }

}
