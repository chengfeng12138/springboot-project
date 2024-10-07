package com.chengfeng.study.myspringbootproject.utils;

import org.apache.commons.mail.HtmlEmail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮箱工具类
 */
public class MailUtils {

    /**
     * 发送qq邮件（方式1）
     * 需导入依赖
     * <dependency>
     * <groupId>org.apache.commons</groupId>
     * <artifactId>commons-email</artifactId>
     * </dependency>
     *
     * @param sender：发送方邮箱
     * @param senderName：发送方姓名
     * @param authCode：授权码
     * @param receiver：接受方邮箱
     * @param subject：主题
     * @param content：内容（可使用HTML标签）
     */
    public static void sendEmail(String sender, String senderName, String authCode,
                                 String receiver, String subject, String content) throws Exception {

        HtmlEmail email = new HtmlEmail();

        /*设置SMTP发送服务器*/
        email.setHostName("smtp.qq.com");
        /*设置需要鉴权端口*/
        email.setSmtpPort(465);
        /*开启 SSL 加密*/
        email.setSSLOnConnect(true);
        /*设置字符编码集*/
        email.setCharset("utf-8");

        /*设置发送人邮箱*/
        email.setFrom(sender, senderName);
        /*设置用户名与授权码*/
        email.setAuthentication(sender, authCode);

        /*收件人邮箱地址*/
        email.addTo(receiver);
        /*设置主题*/
        email.setSubject(subject);
        /*设置内容*/
        email.setMsg(content);

        email.send();
    }


    /**
     * 发送qq邮件（方式2）
     * 需导入依赖
     * <dependency>
     * <groupId>org.apache.commons</groupId>
     * <artifactId>commons-email</artifactId>
     * </dependency>
     *
     * @param sender：发送方邮箱
     * @param senderName：发送方姓名
     * @param authCode：授权码
     * @param receiver：接受方邮箱
     * @param subject：主题
     * @param content：内容（可使用HTML标签）
     */
    public static void sendMail(String sender, String senderName, String authCode,
                                String receiver, String subject, String content) throws Exception {

        /*设置Session链接属性*/
        Properties props = new Properties();
        /*设置SMTP发送服务器*/
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.host", "smtp.qq.com");
        /*设置验证*/
        props.setProperty("mail.smtp.auth", "true");

        /* 创建验证器*/
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                /*设置用户名与授权码*/
                return new PasswordAuthentication(sender, authCode);
            }
        };

        /*创建 Session邮件服务器会话对象 Session*/
        Session session = Session.getInstance(props, auth);

        /*创建一个Message，它相当于是邮件内容*/
        Message message = new MimeMessage(session);
        /*设置发送者与姓名*/
        message.setFrom(new InternetAddress(sender, senderName));
        /*设置发送方式与接收者*/
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        /*设置标题与内容*/
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=utf-8");

        /*创建 Transport用于将邮件发送*/
        Transport.send(message);
    }


    /**
     * 发送163邮件（方式1）
     * 需导入依赖
     * <dependency>
     * <groupId>org.apache.commons</groupId>
     * <artifactId>commons-email</artifactId>
     * </dependency>
     *
     * @param sender：发送方邮箱
     * @param senderName：发送方姓名
     * @param authCode：授权码
     * @param receiver：接受方邮箱
     * @param subject：主题
     * @param content：内容（可使用HTML标签）
     */
    public static void sendEmails(String sender, String senderName, String authCode,
                                  String receiver, String subject, String content) throws Exception {

        HtmlEmail email = new HtmlEmail();

        /*设置SMTP发送服务器*/
        email.setHostName("smtp.163.com");
        /*设置需要鉴权端口*/
        email.setSmtpPort(465);
        /*开启 SSL 加密*/
        email.setSSLOnConnect(true);
        /*设置字符编码集*/
        email.setCharset("utf-8");

        /*设置发送人邮箱*/
        email.setFrom(sender, senderName);
        /*设置用户名与授权码*/
        email.setAuthentication(sender, authCode);

        /*收件人邮箱地址*/
        email.addTo(receiver);
        /*设置主题*/
        email.setSubject(subject);
        /*设置内容*/
        email.setMsg(content);

        email.send();
    }


    /**
     * 发送163邮件（方式2）
     * 需导入依赖
     * <dependency>
     * <groupId>org.apache.commons</groupId>
     * <artifactId>commons-email</artifactId>
     * </dependency>
     *
     * @param sender：发送方邮箱
     * @param senderName：发送方姓名
     * @param authCode：授权码
     * @param receiver：接受方邮箱
     * @param subject：主题
     * @param content：内容（可使用HTML标签）
     */
    public static void sendMails(String sender, String senderName, String authCode,
                                 String receiver, String subject, String content) throws Exception {

        /*设置Session链接属性*/
        Properties props = new Properties();
        /*设置SMTP发送服务器*/
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.host", "smtp.163.com");
        /*设置验证*/
        props.setProperty("mail.smtp.auth", "true");

        /* 创建验证器*/
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                /*设置用户名与授权码*/
                return new PasswordAuthentication(sender, authCode);
            }
        };

        /*创建 Session邮件服务器会话对象 Session*/
        Session session = Session.getInstance(props, auth);

        /*创建一个Message，它相当于是邮件内容*/
        Message message = new MimeMessage(session);
        /*设置发送者与姓名*/
        message.setFrom(new InternetAddress(sender, senderName));
        /*设置发送方式与接收者*/
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        /*设置标题与内容*/
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=utf-8");

        /*创建 Transport用于将邮件发送*/
        Transport.send(message);
    }

    public static void main(String[] args) throws Exception {
        MailUtils.sendMail("228311670@qq.com", "Java Utils", "yecsdpzajakacbcj",
                "228311670@qq.com", "测试发送1", "测试发送内容11111");
    }

}
