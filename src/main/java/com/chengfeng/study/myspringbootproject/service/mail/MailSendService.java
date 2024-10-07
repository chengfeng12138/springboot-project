package com.chengfeng.study.myspringbootproject.service.mail;

import cn.hutool.core.util.StrUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * MailSendService class
 *
 * @author chengfeng
 * @date 2021/8/15 /0015 15:46
 */
@Service
public class MailSendService {

    @Resource
    JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     * @param subject 主题
     * @param text 内容
     * @param from 发送者
     * @param to 收件人
     * @author chengfeng
     * @date 2021/8/15 /0015 15:21
     **/
    public void sendMail(String subject, String text, String from, String to) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //主题
        simpleMailMessage.setSubject(subject);
        //内容
        simpleMailMessage.setText(text);
        //发送者
        simpleMailMessage.setFrom(from);
        //收件人
        simpleMailMessage.setTo(to);
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送邮件
     * @param subject 主题
     * @param text 内容
     * @param from 发送者
     * @param to 收件人
     * @param isHtml 是否将内容解析为html
     * @param fileName 附件名
     * @param fileUrl 附件地址
     * @author chengfeng
     * @date 2021/8/15 /0015 15:21
     **/
    public void sendMail(String subject, String text, String from, String to, boolean isHtml, String fileName, String fileUrl) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //主题
            helper.setSubject(subject);
            //内容
            helper.setText(text, isHtml);
            if (StrUtil.isNotEmpty(fileName) && StrUtil.isNotEmpty(fileUrl)) {
                helper.addAttachment(fileName, new File(fileUrl));
            }
            //发送者
            helper.setFrom(from);
            //收件人
            helper.setTo(to);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送异常...");
        }
    }
}
