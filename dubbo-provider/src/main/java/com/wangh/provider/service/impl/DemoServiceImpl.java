package com.wangh.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoze.api.service.DemoService;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * DemoServiceImpl 服务提供类
 *
 * @author wangh
 * @date 2021/12/10
 */
@Service(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {

    //内网ip
    public static String INTRANET_IP = getIntranetIp();

    //外网ip
    public static String INTERNET_IP = getInternetIp();


    @Override
    public String sayHello() throws UnknownHostException {
        return "hello！dubbo通信正常。" + "\n\r" + "远端机ip：" +DemoServiceImpl.INTERNET_IP;
    }

    /**
     * 获得内网IP
     *
     * @return 内网IP
     */
    private static String getIntranetIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     *
     * @return 外网IP
     */
    private static String getInternetIp() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements()) {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements()) {
                    ip = addrs.nextElement();
                    if (ip != null
                        && ip instanceof Inet4Address
                        && ip.isSiteLocalAddress()
                        && !ip.getHostAddress().equals(INTRANET_IP)) {
                        return ip.getHostAddress();
                    }
                }
            }

            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
