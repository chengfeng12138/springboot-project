package com.chengfeng.study.myspringbootproject.controller.login;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.pojo.UserToken;
import com.chengfeng.study.myspringbootproject.service.user.UserTokenService;
import com.chengfeng.study.myspringbootproject.utils.ResultUtil;
import com.chengfeng.study.myspringbootproject.websocket.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ScanLogin class
 *
 * @author chengfeng
 * @date 2021/7/3 /0003 20:01
 */
@Controller
public class ScanLoginController {
    private static final Logger log = Logger.getLogger(ScanLoginController.class.getName());

    @Autowired
    UserTokenService userTokenService;
    /**
     * @description 创建登录二维码
     * @author chengfeng
     * @date 2021/7/3 /0003 20:03
     **/
    @RequestMapping(value = "/getLoginQr", method = RequestMethod.GET)
    public void createCodeImg(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        try {
            //生成uuid并保存到数据库
            String uuid = IdUtil.simpleUUID();
            UserToken userToken = new UserToken();
            userToken.setUuid(uuid);
            userToken.setCreateTime(DateUtil.now());
            userToken.setState(0);
            System.out.println(userToken);
            //保存到数据库
            userTokenService.insertUserToken(userToken);
            //设置响应头部信息
            response.setHeader("uuid", uuid);
            response.addHeader("Access-Control-Expose-Headers", "uuid");
            String codeUrl = "http://" + "192.168.0.106" + ":8080/phoneLogin?uuid=" + uuid;
            log.log(Level.INFO, "二维码绑定url: " + codeUrl);
            QrCodeUtil.generate(codeUrl, 300, 300, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * @description 扫码后的请求处理
    * @author chengfeng
    * @date 2021/7/4 /0004 20:52
    **/
    @RequestMapping(value = "/phoneLogin", method = RequestMethod.GET)
    public String getPhonePage(@RequestParam("uuid") String uuid) {
        log.log(Level.INFO, "用户 " + uuid + " 扫码登录, 等待手机端确认...");
        ResponseResult success = ResultUtil.success("用户 " + uuid + " 扫码登录, 等待手机端确认...", "waitPhoneConfirmation");
        WebSocketClient.appointSending(uuid, JSON.toJSONString(success));
        return "phoneLogin";
    }

    /**
     * @description 手机端确认登录后的处理
     * @author chengfeng
     * @date 2021/7/4 /0004 20:52
     **/
    @RequestMapping(value = "/bindUserIdAndToken", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult bindUserIdAndToken(@RequestParam("uuid") String uuid,
                                             @RequestParam("name") String name,
                                             @RequestParam("password") String password) {
        log.log(Level.INFO, "扫码登录确认. uuid: " + uuid + ", 登录用户名: " + name + ", 密码: " + password);
        return userTokenService.getUserToken(uuid, name, password);
    }
}
