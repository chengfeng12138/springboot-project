package com.chengfeng.study.myspringbootproject.utils;

import cn.hutool.core.net.NetUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * RequestUtil class
 *
 * @author chengfeng
 * @date 2020/7/26 /0026 17:39
 */
public class RequestUtil {
    public static String getIpAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || Fields.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || Fields.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || Fields.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || Fields.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || Fields.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
    * @description 获取本机ip
    * @author chengfeng
    * @date 2021/7/10 /0010 17:49
    **/
    public static String getLocalIp() throws SocketException {
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();
                if (ip != null && ip.isSiteLocalAddress() && ip instanceof Inet4Address) {
                    String ipAddress = ip.getHostAddress();
                    if ("192.168.122.1".equals(ipAddress)) {
                        continue;
                    }
                    return ipAddress;
                }
            }
        }
        return "127.0.0.1";
    }

    public static void main(String[] args) throws SocketException {
        System.out.println(RequestUtil.getLocalIp());
        System.out.println(NetUtil.localIpv4s());
        System.out.println(NetUtil.getLocalhostStr());
        System.out.println(NetUtil.getLocalhost());
        System.out.println(NetUtil.getNetworkInterfaces());
    }
}
