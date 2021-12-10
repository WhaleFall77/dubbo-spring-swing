package com.wangh.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoze.api.service.DemoService;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoConsumerController
 * 页面调用dubbo
 * @author wangh
 * @date 2021/12/10
 */
@RestController
public class DemoConsumerController {

    //dubbo直连时后面加上url
//    @Reference(version = "${demo.service.version}",url = "127.0.0.1:20880")
    @Reference(version = "${demo.service.version}")
    private DemoService demoService;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) throws UnknownHostException {
        return demoService.sayHello();
    }

}
