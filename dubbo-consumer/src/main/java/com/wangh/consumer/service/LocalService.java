/**
 * Copyright(C),2015‐2021,北京清能互联科技有限公司
 */
package com.wangh.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoze.api.service.DemoService;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 本地服务
 *
 * @Author: wanghao@tsintergy.com
 * @Date: 2021/12/10 10:45
 * @Version: 1.0.0
 */
@Service
public class LocalService {

    @Value("remote.ip")
    String remoteIp;

    @Reference(version = "${demo.service.version}",url = "${remote.ip}")
    private DemoService demoService;


    public String saySomething() throws UnknownHostException {
        return demoService.sayHello();
    }

}