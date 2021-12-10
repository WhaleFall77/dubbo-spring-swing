package com.wangh.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoze.api.service.DemoService;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * DemoServiceImpl
 * 服务提供类
 * @author wangh
 * @date 2021/12/10
 */
@Service(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello() throws UnknownHostException {
        InetAddress ip4 = Inet4Address.getLocalHost();
        return "hello！dubbo通信正常。"+"\n\r"+"远端机ip："+ip4.getHostAddress();
    }
}
